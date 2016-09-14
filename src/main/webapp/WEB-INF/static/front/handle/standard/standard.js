Entities.Standard = (function(Backbone, Entities,_) {
    var base = Entities.config.apiUrl;
    var API_SAVE =base+'/standard/add';//添加标准管理
    var API_EDIT = base+'/standard/edit';//编辑标准管理
    var API_QUERY = base+'/standard/queryDetail';//查询指定id内容
    var API_FETCH = base +'/standard/query';//查询标准管理
    var API_DESTROY = base+'/standard/delete';//删除标准管理
    var API_COUNT = base+'/standard/queryCount';//查询记录总数
    var Model = Backbone.Model.extend({
        idAttribute: 'id',
        edit: function(data){
            var model = this;
            data = _.extend({id:model.id},data);
            return Entities.sync(API_EDIT,data).then(function(res){
                model.set(_.extend(data,res));
            });
        },
        delete: function(){
            var model = this;
            var data = {id:model.id};
            return Entities.sync(API_DESTROY,data).then(function(res){
                model.trigger('destroy', model, model.collection,{removeself:true});
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
        model:Model,
        fetch: function(data){
            var collection=this;
            return Entities.sync(API_FETCH,data).then(function(res){
                collection.reset(res);
            });
        },
        query:function(data){
            var collection=this;
            return Entities.sync(API_QUERY,data).then(function(res){
                collection.reset(res);
            });
        },
        count:function(data){
            return Entities.sync(API_COUNT,data).then(function(res){
               return res.count;
            });
        },
        create: function(data){
            var collection=this;
            return Entities.sync(API_SAVE,data).then(function(res){
                collection.unshift(_.extend(data,res));
            });
        }
    });
    return {
        Model: Model,
        Collection: Collection
    };
}(Backbone,Entities,_));
