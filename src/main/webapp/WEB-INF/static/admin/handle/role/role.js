/**
 * Created by huchao on 2016/7/10.
 */
Entities.Role = (function(Backbone, Entities,_) {

    var base = Entities.config.apiUrl;
    var API_SAVE =base+'/role/add';//添加角色
    var API_EDIT = base+'/role/edit';//编辑角色
    var API_QUERY = base+'/user/getRoles';//根据用户id查找角色
    var API_FETCH = base +'/role/query';//查询角色管理
    var API_DESTROY = base+'/role/delete';//删除角色管理
    var API_PERSSION = base+'/role/getTree';//获取角色权限
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
        create: function(data){
            var collection=this;
            return Entities.sync(API_SAVE,data).then(function(res){
                collection.unshift(_.extend(data,res));
            });
        },
        tree:function(data){

            return Entities.sync(API_PERSSION,data).then(function(res){
                return res;
            });
        }
    });
    return {
        Model: Model,
        Collection: Collection
    };
}(Backbone,Entities,_));
