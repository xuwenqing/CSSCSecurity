/**
 * Created by huchao on 2016/8/2.
 */
Entities.Login = (function (Backbone, Entities, _) {
    var base = Entities.config.apiUrl;
    var API_login = base + '/login';//ÓÃ»§µÇÂ¼
    var Model = Backbone.Model.extend({
        login: function (data) {
            return Entities.formSync(API_login, data).then(function (res) {
                return res;
            },function(res){
                return res;
            });
        }
    });
    return {
        Model: Model
    };
}(Backbone, Entities, _));
