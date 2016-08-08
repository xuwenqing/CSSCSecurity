/**
 * Created by huchao on 2016/8/5.
 */
Entities.Rejister = (function (Backbone, Entities, _) {
    var base = Entities.config.apiUrl;
    var API_REG = base + '/register';//ÓÃ»§µÇÂ¼
    var Model = Backbone.Model.extend({
        reg: function (data) {
            return Entities.sync(API_REG, data).then(function (res) {
                return res.status;
            },function(res){
                return res;
            });
        }
    });
    return {
        Model: Model
    };
}(Backbone, Entities, _));