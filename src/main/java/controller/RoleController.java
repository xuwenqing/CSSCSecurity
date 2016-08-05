package controller;

import controller.dto.LongIdDto;
import controller.dto.ResponsePackDto;
import controller.dto.RoleDto;
import controller.dto.TreeNode;
import dao.condition.RoleCondition;
import model.Resource;
import model.Role;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ResourceService;
import service.RoleService;

import java.util.*;

/**
 * Created by wenqing on 2016/6/6.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("role:create")
    @RequestMapping("/add")
    public
    @ResponseBody
    ResponsePackDto add(@RequestBody Role role) {
//        Role role = new Role();
//        role.setId(dto.getId());
//        role.setRole(dto.getRole());
//        role.setDescription(dto.getDescription());
//        role.setAvailable(dto.getAvailable());
//        roleService.correlationResources(role.getId(), dto.getIds());
        ResponsePackDto dto = new ResponsePackDto();
        role = roleService.createRole(role);
        if(role == null) {
            dto.setStatus(500);
            dto.setError("角色信息更新失败");
        }
        return dto;
    }

    @RequiresPermissions("role:delete")
    @RequestMapping("/delete")
    public
    @ResponseBody
    ResponsePackDto delete(@RequestBody LongIdDto roleId) {
        roleService.deleteRole(roleId.getId());
        return null;
    }

    /**
     * 获取所有权限列表
     * @return
     */
    @RequiresPermissions(value={"role:view","role:update","role:create"},logical= Logical.OR)
    @RequestMapping(value = "/getResources",method = RequestMethod.GET)
    public
    @ResponseBody
    ResponsePackDto getResources() {
        ResponsePackDto dto = new ResponsePackDto();
        List<Resource> roles = resourceService.query();
        dto.setData(roles);
        return dto;
    }

    /**
     * 获取某一角色的权限列表
     * @return
     */
    @RequiresPermissions(value={"role:view","role:update","role:create"},logical= Logical.OR)
    @RequestMapping(value = "/getResources",method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto getResources(@RequestBody LongIdDto idDto) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Resource> roles = resourceService.queryByRoleId(idDto.getId());
        dto.setData(roles);
        return dto;
    }

    @RequiresPermissions(value={"role:view","role:update","role:create"},logical= Logical.OR)
    @RequestMapping(value = "/getTree",method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto getTree(@RequestBody LongIdDto idDto) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Resource> resources = resourceService.queryByRoleId(idDto.getId());

        List<TreeNode> treeNodes = initTree();

        if(resources != null && !resources.isEmpty()) {
            for(Resource resource : resources) {

                TreeNode newnode = new TreeNode(resource.getId());
                int idx = treeNodes.indexOf(newnode);
                if(idx >= 0) {
                    TreeNode node = treeNodes.get(idx);
                    node.setChecked(true);
                }

            }
        }

        dto.setData(treeNodes);
        return dto;
    }

    private List<TreeNode> initTree() {
        //    //id:1, pId:0, name:"所有权限", title:"", checked:true, open:true
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(new TreeNode(1L,0L,"所有权限","",false,true,true));

        nodes.add(new TreeNode(51L,1L,"标准管理","",false,true,true));
        nodes.add(new TreeNode(52L,51L,"标准新增","",false,false,false));
        nodes.add(new TreeNode(53L,51L,"标准修改","",false,false,false));
        nodes.add(new TreeNode(54L,51L,"标准删除","",false,false,false));
        nodes.add(new TreeNode(55L,51L,"标准查看","",false,false,false));

        nodes.add(new TreeNode(61L,1L,"法律管理","",false,true,true));
        nodes.add(new TreeNode(62L,61L,"法律新增","",false,false,false));
        nodes.add(new TreeNode(63L,61L,"法律修改","",false,false,false));
        nodes.add(new TreeNode(64L,61L,"法律删除","",false,false,false));
        nodes.add(new TreeNode(65L,61L,"法律查看","",false,false,false));

        nodes.add(new TreeNode(71L,1L,"安全管理","",false,true,true));
        nodes.add(new TreeNode(72L,71L,"安全新增","",false,false,false));
        nodes.add(new TreeNode(73L,71L,"安全修改","",false,false,false));
        nodes.add(new TreeNode(74L,71L,"安全删除","",false,false,false));
        nodes.add(new TreeNode(75L,71L,"安全查看","",false,false,false));

        nodes.add(new TreeNode(81L,1L,"工装管理","",false,true,true));
        nodes.add(new TreeNode(82L,81L,"工装新增","",false,false,false));
        nodes.add(new TreeNode(83L,81L,"工装修改","",false,false,false));
        nodes.add(new TreeNode(84L,81L,"工装删除","",false,false,false));
        nodes.add(new TreeNode(85L,81L,"工装查看","",false,false,false));

        nodes.add(new TreeNode(91L,1L,"案例管理","",false,true,true));
        nodes.add(new TreeNode(92L,91L,"案例新增","",false,false,false));
        nodes.add(new TreeNode(93L,91L,"案例修改","",false,false,false));
        nodes.add(new TreeNode(94L,91L,"案例删除","",false,false,false));
        nodes.add(new TreeNode(95L,91L,"案例查看","",false,false,false));

        nodes.add(new TreeNode(101L,1L,"技术管理","",false,true,true));
        nodes.add(new TreeNode(102L,101L,"技术新增","",false,false,false));
        nodes.add(new TreeNode(103L,101L,"技术修改","",false,false,false));
        nodes.add(new TreeNode(104L,101L,"技术删除","",false,false,false));
        nodes.add(new TreeNode(105L,101L,"技术查看","",false,false,false));

        nodes.add(new TreeNode(21L,1L,"用户管理","",false,true,true));
        nodes.add(new TreeNode(22L,21L,"用户新增","",false,false,false));
        nodes.add(new TreeNode(23L,21L,"用户修改","",false,false,false));
        nodes.add(new TreeNode(24L,21L,"用户删除","",false,false,false));
        nodes.add(new TreeNode(25L,21L,"用户查看","",false,false,false));

        nodes.add(new TreeNode(41L,1L,"角色管理","",false,true,true));
        nodes.add(new TreeNode(42L,41L,"角色新增","",false,false,false));
        nodes.add(new TreeNode(43L,41L,"角色修改","",false,false,false));
        nodes.add(new TreeNode(44L,41L,"角色删除","",false,false,false));
        nodes.add(new TreeNode(45L,41L,"角色查看","",false,false,false));


        return nodes;
    }

    /**
     *
     * @param dto
     * @return
     */
    @RequiresPermissions("role:update")
    @RequestMapping("/edit")
    public
    @ResponseBody
    ResponsePackDto edit(@RequestBody RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setRole(dto.getRole());
        role.setDescription(dto.getDescription());
        role.setAvailable(dto.getAvailable());
        roleService.updateRole(role, dto.getIds());
        return new ResponsePackDto();
    }

    @RequiresPermissions("role:view")
    @RequestMapping("/query")
    public
    @ResponseBody
    ResponsePackDto query(@RequestBody(required = false) RoleCondition condition) {
        ResponsePackDto dto = new ResponsePackDto();
        if (condition == null)
            condition = new RoleCondition();
        if (condition.getSortby() == null) {
            condition.setSortby("role");
        }
        dto.setData(roleService.query(condition));
        return dto;
    }

    @RequiresPermissions("role:view")
    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto queryCount(@RequestBody(required = false) RoleCondition condition) {
        if(condition == null)
            condition = new RoleCondition();
        int count = roleService.queryCount(condition);
        Map<String,Integer> map = new HashMap<String,Integer>(1);
        map.put("count",count);
        return new ResponsePackDto(map);
    }
}
