window.Entities = (function() {
    var Entities = {};
    Entities.config = {
        apiEndpoint: ''
    };
    Entities.sync = function(url, data, options) {
        var data = $.toJSON(data);
        options = options || {};
        var params = {
            url: url,
            type: options.type || 'post',
            data: data,
            contentType: 'application/json',
            dataType: 'json'
        };
        var xhr = options.xhr = $.ajax(params);
        // 处理链式调用情况
        return xhr.then(function(res, textStatus, jqXHR) {
            if (res.status == 0) {
                console.log(res.status);
                return res.data;
            } else {
                console.log(res.status);
                return $.Deferred().reject(jqXHR, data, 'INROAD_ERROR').promise();
            }
        });
    };
    return Entities
}());