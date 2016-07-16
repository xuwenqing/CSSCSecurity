/**
 * Created by huchao on 2016/7/8.
 */
Entities.Health = (function (Backbone, Entities, _) {
    var base = 'http://115.159.114.88';
    var API_SAVE = base + '/health/add';//添加安全职业健康
    var API_EDIT = base + '/health/edit';//编辑安全职业健康
    var API_QUERY = base + '/health/queryDetail';//查询指定id内容
    var API_FETCH = base + '/health/query';//查询安全职业健康
    var API_DESTROY = base + '/health/delete';//删除安全职业健康
    var API_DELETES = base + '/health/deletes';//删除安全职业健康
    var Model = Backbone.Model.extend({
        idAttribute: 'id',
        edit: function (data) {
            var model = this;
            data = _.extend({id: model.id}, data);
            console.log(data);
            return Entities.sync(API_EDIT, data).then(function (res) {
                model.set(_.extend(data, res));
            });
        },
        delete: function () {
            var model = this;
            var data = {id: model.id};
            return Entities.sync(API_DESTROY, data).then(function (res) {
                model.trigger('destroy', model, model.collection, {removeself: true});
            });
        },
    });

    var Collection = Backbone.Collection.extend({
        model: Model,
        fetch: function (data) {
            console.log(data);
            var collection = this;
            return Entities.sync(API_FETCH, data).then(function (res) {
                collection.reset(res);
            });
        },
        query: function (data) {
            var collection = this;
            return Entities.sync(API_QUERY, data).then(function (res) {
                console.log(res);
                collection.reset(res);
            });
        },
        create: function (data) {
            var collection = this;
            return Entities.sync(API_SAVE, data).then(function (res) {
                collection.unshift(_.extend(data,res));
            });
        },
        deletes: function (data) {
            var collection = this;
            return Entities.sync(API_DELETES, data).then(function (res) {
                collection.unshift(_.extend(data,res));
            });
        }
    });
    return {
        Model: Model,
        Collection: Collection
    };
}(Backbone, Entities, _));
