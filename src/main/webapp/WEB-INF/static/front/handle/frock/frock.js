/**
 * Created by huchao on 2016/7/8.
 */
Entities.Frock = (function (Backbone, Entities, _) {
    var base = Entities.config.apiUrl;
    var API_SAVE = base + '/frock/add';//��Ӱ�ȫְҵ����
    var API_EDIT = base + '/frock/edit';//�༭��ȫְҵ����
    var API_QUERY = base + '/frock/queryDetail';//��ѯָ��id����
    var API_FETCH = base + '/frock/query';//��ѯ��ȫְҵ����
    var API_DESTROY = base + '/frock/delete';//ɾ��ȫְҵ����
    var API_DELETES = base + '/frock/deletes';//ɾ��ȫְҵ����
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
        fetch: function(data){
        	 var model = this;
            data = _.extend({id: model.id}, data);
            return Entities.sync(API_QUERY, data).then(function (res) {
                model.set(_.extend(data, res));
            });
        }
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
