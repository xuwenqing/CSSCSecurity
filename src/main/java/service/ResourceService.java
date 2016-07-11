package service;

import model.Resource;

import java.util.List;

public interface ResourceService {
     Resource createResource(Resource resource);
     void deleteResource(Long resourceId);
     void updateResource(Resource resource);
     List<Resource> query();
     List<Resource> queryByRoleId(Long id);
}
