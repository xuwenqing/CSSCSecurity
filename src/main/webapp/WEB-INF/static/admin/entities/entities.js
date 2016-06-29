window.Entities = (function() {

    var Entities = {};

    Entities.config = {
        apiEndpoint: ''
    };
    Entities.sync = function(url, data, options) {
        options = options || {};
        if (/^https?/.test(url)) {
            url = url;
        } else {
            url = Entities.config.apiEndpoint + url;
        }
        var params = {
            url: url,
            type: options.type || 'post',
            data: data,
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json'
        };
        // 不用 success error 作为回调函数
        // if (options.success) {
        // 	var success = options.success;
        // 	options.success = function(response) {
        // 		if (response.status == 1) success(response.data);
        // 	}
        // }

        // // Pass along `textStatus` and `errorThrown` from jQuery.
        // var error = options.error;
        // options.error = function(xhr, textStatus, errorThrown) {
        // 	options.textStatus = textStatus;
        // 	options.errorThrown = errorThrown;
        // 	if (error) error.call(options.context, xhr, textStatus, errorThrown);
        // };

        // Make the request, allowing the user to override any Ajax options.
        var xhr = options.xhr = $.ajax(_.extend(params, options));

        // 处理链式调用情况
        return xhr.then(function(res, textStatus, jqXHR) {
            if (res.status == 1) {
                return res.data;
            } else {
                return $.Deferred().reject(jqXHR, data, 'INROAD_ERROR').promise();
            }
        });
    };
    return Entities
}());