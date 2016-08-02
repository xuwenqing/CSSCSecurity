/**
 * Created by huchao on 2016/8/2.
 */
Entities.Login = (function (Backbone, Entities, _) {
    var base = Entities.config.apiUrl;
    var API_login = base + '/login';//ÓÃ»§µÇÂ¼
    var Model = Backbone.Model.extend({
        idAttribute: 'id',
        login: function (data) {
          /*  var model = this;
            //data = _.extend({id: model.id}, data);*/
            return Entities.formSync(API_login, data).then(function (res) {
                console.log("res login js");
                console.log(res);
                return res;
            });
        }
    });
    return {
        Model: Model
    };
}(Backbone, Entities, _));
