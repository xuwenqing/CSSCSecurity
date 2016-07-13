package controller;

import controller.dto.LongIdDto;
import controller.dto.ResponsePackDto;
import controller.dto.RoleDto;
import controller.dto.TreeNode;
import dao.condition.RoleCondition;
import model.Resource;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ResourceService;
import service.RoleService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    @RequestMapping(value = "/getResources",method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto getResources(@RequestBody LongIdDto idDto) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Resource> roles = resourceService.queryByRoleId(idDto.getId());
        dto.setData(roles);
        return dto;
    }

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

        return nodes;
    }

    /**
     *
     * @param dto
     * @return
     */
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
}
