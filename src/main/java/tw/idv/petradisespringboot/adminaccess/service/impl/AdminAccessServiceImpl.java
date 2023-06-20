//package tw.idv.petradisespringboot.adminaccess.service.impl;
//
//import tw.idv.petradisespringboot.adminaccess.repo.AdminAccessRepository;
//import tw.idv.petradisespringboot.adminaccess.service.AdminAccessService;
//import tw.idv.petradisespringboot.adminaccess.vo.AdminAccess;
//
//public class AdminAccessServiceImpl implements AdminAccessService {
//
//    private final AdminAccessRepository repository;
//
//    public AdminAccessServiceImpl(AdminAccessRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public AdminAccess add(AdminAccess adminAccess) {
//        return repository.save(adminAccess);
//    }
//
//    @Override
//    public Boolean delete(AdminAccess adminAccess) {
//        return true;
//    }
//}
