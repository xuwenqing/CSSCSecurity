package service;

import model.Resource;

public interface ResourceService {
     Resource createResource(Resource permission);
     void deleteResource(Long resourceId);
}
