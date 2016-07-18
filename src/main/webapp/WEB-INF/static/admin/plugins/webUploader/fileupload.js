window.webUploader=function (file_meta) {
    var userInfo = {userId: "kazaff", md5: ""};   //用户会话信息
    var chunkSize = 5000 * 1024;        //分块大小
    var uniqueFileName = null;          //文件唯一标识符
    var md5Mark = null;
    var state = 'pending';
    var $list = $('#theList');
    var $btn = $('#ctlBtn');
    //var file_meta = new Array();

    var backEndUrl = "http://localhost:8080/file/upload";
    var deleteUrl = "http://localhost:8080/file/delete";

    WebUploader.Uploader.register({
        "before-send-file": "beforeSendFile"
        , "before-send": "beforeSend"
        , "after-send-file": "afterSendFile"
    }, {
        beforeSendFile: function (file) {
            //秒传验证
            var task = new $.Deferred();
            var start = new Date().getTime();
            (new WebUploader.Uploader()).md5File(file, 0, 10 * 1024 * 1024).progress(function (percentage) {
                //console.log(percentage);
            }).then(function (val) {
                console.log("总耗时: " + ((new Date().getTime()) - start) / 1000);

                md5Mark = val;
                userInfo.md5 = val;

                $.ajax({
                    type: "POST"
                    , url: backEndUrl
                    , data: {
                        status: "md5Check"
                        , md5: val
                    }
                    , cache: false
                    , timeout: 1000 //todo 超时的话，只能认为该文件不曾上传过
                    , dataType: "json"
                }).then(function (data, textStatus, jqXHR) {

                    if (data.ifExist) {   //若存在，这返回失败给WebUploader，表明该文件不需要上传
                        task.reject();

                        uploader.skipFile(file);
                        file.path = data.path;
                        UploadComplete(file);
                    } else {
                        task.resolve();
                        //拿到上传文件的唯一名称，用于断点续传
                        uniqueFileName = md5('' + userInfo.userId + file.name + file.type + file.lastModifiedDate + file.size);
                    }
                }, function (jqXHR, textStatus, errorThrown) {    //任何形式的验证失败，都触发重新上传
                    task.resolve();
                    //拿到上传文件的唯一名称，用于断点续传
                    uniqueFileName = md5('' + userInfo.userId + file.name + file.type + file.lastModifiedDate + file.size);
                });
            });
            return $.when(task);
        }
        , beforeSend: function (block) {
            //分片验证是否已传过，用于断点续传
            var task = new $.Deferred();
            $.ajax({
                type: "POST"
                , url: backEndUrl
                , data: {
                    status: "chunkCheck"
                    , name: uniqueFileName
                    , chunkIndex: block.chunk
                    , size: block.end - block.start
                }
                , cache: false
                , timeout: 1000 //todo 超时的话，只能认为该分片未上传过
                , dataType: "json"
            }).then(function (data, textStatus, jqXHR) {
                if (data.ifExist) {   //若存在，返回失败给WebUploader，表明该分块不需要上传
                    task.reject();
                } else {
                    task.resolve();
                }
            }, function (jqXHR, textStatus, errorThrown) {    //任何形式的验证失败，都触发重新上传
                task.resolve();
            });

            return $.when(task);
        }
        , afterSendFile: function (file, data) {
            var chunksTotal = 0;
            if ((chunksTotal = Math.ceil(file.size / chunkSize)) > 1) {
                //合并请求
                var task = new $.Deferred();
                $.ajax({
                    type: "POST"
                    , url: backEndUrl
                    , data: {
                        status: "chunksMerge"
                        , name: uniqueFileName
                        , chunks: chunksTotal
                        , ext: file.ext
                        , size: file.size
                        , md5: md5Mark
                        , originName: file.name
                    }
                    , cache: false
                    , dataType: "json"
                }).then(function (data, textStatus, jqXHR) {

                    //todo 检查响应是否正常
                    task.resolve();

                    var meta = $.toJSON(data);//上传文件 返回信息
                    file_meta.push(meta);
                    //$('#file_meta').val(file_meta);

                    file.path = data.filepath;
                    UploadComplete(file, meta);

                }, function (jqXHR, textStatus, errorThrown) {
                    task.reject();
                });

                return $.when(task);
            } else {
                var res = eval('(' + data._raw + ')');
                var meta = $.toJSON(res);//上传文件 返回信息
                file_meta.push(meta);
                //$('#file_meta').val(file_meta);

                file.path = res.filepath;
                UploadComplete(file, meta);
            }
        }
    });

    var uploader = WebUploader.create({
        swf: "Uploader.swf"
        , server: backEndUrl
        , pick: "#picker"
        , resize: false
        , dnd: "#theList"
        , paste: document.body
        , disableGlobalDnd: true
        , thumb: {
            width: 100
            , height: 100
            , quality: 70
            , allowMagnify: true
            , crop: true
        }
        , compress: false
        , prepareNextFile: true
        , chunked: true
        , chunkSize: chunkSize
        , threads: true
        , formData: function () {
            return $.extend(true, {}, userInfo);
        }
        , fileNumLimit: 20
        , fileSingleSizeLimit: 8 * 1024 * 1024 * 1024 // 8G
        , duplicate: true
    });

    uploader.on("fileQueued", function (file) {

        //var $li = $('<li id="'+file.id+'">' +
        //    '<img /><span>'+file.name+'</span><span class="itemUpload">上传</span><span class="itemStop">暂停</span><span class="itemDel">删除</span>' +
        //
        //    '</li>');

        var $li = $('<li id="' + file.id + '">' +
            '<img /><span>' + file.name + '</span><span class="itemDel">删除</span>' +
            '<div class="percentage"></div>' +
            '</li>');

        //$li.find('.progress .progress-bar');
        $li.appendTo($list);

        var $img = $("#" + file.id).find("img");

        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith("<span>不能预览</span>");
            }

            $img.attr("src", src);
        });

        //todo 如果要删除的文件正在上传（包括暂停），则需要发送给后端一个请求用来清除服务器端的缓存文件
        $li.find(".itemDel").on("click", function () {
            uploader.removeFile(file.id);	//从上传文件列表中删除
            $(this).parent().remove();	//从上传列表dom中删除
            removeFile(uniqueFileName);
        });

        //$li.find(".itemUpload").on("click", function(){
        //
        //    uploader.upload(file.id);
        //
        //    $(this).hide();
        //    $(this).parent().find(".itemStop").show();
        //});
        //
        //$li.find(".itemStop").on("click", function(){
        //    uploader.stop(file,true);
        //
        //    //"暂停"-->"上传"
        //    $(this).hide();
        //    $(this).parent().find(".itemUpload").show();
        //});
    });

    //uploader.on("uploadProgress", function(file, percentage){
    //    $("#" + file.id + " .percentage").text(percentage * 100 + "%");
    //});

    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }
        //$(".progress").show();
        $percent.css('width', percentage * 100 + '%');
        $("#" + file.id + " .percentage").text(Math.floor(percentage * 100) + "%");
        //$percent.text( + '%' )
    });

    uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });

    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop(true);
        } else {
            uploader.upload();
        }
    });

    function UploadComplete(file, file_meta) {

        $("#" + file.id + " .percentage").text("上传完毕");
        $("#" + file.id).find(".itemStop").hide();
        $("#" + file.id).find(".itemUpload").hide();

        $("#" + file.id).find(".itemDel").unbind("click");

        $("#" + file.id).find(".itemDel").on("click", function () {
            uploader.removeFile(file.id);	//从上传文件列表中删除
            $(this).parent().remove();	//从上传列表dom中删除
            removeFile(file.path, file_meta)
        });
    }

    //发送请求删除已上传文件
    function removeFile(filename, meta) {

        if (meta !== null) {
            if (file_meta.indexOf(meta) !== -1) {

                var idx = file_meta.indexOf(meta);
                file_meta.splice(idx, 1);

            }
            //$('#file_meta').val(file_meta);
        }

        $.ajax({
            type: 'POST',
            url: deleteUrl,
            data: {
                name: filename
            },
            dataType: 'json',
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                //错误提示
                console.log('error');
            }
        });
    }
}