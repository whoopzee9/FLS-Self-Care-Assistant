package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.model.Role
import selfcareassistant.repository.RoleRepo
import selfcareassistant.service.RoleService
import java.util.*

@Service
class RoleServiceImpl: RoleService {

    @Autowired
    lateinit var roleRepo: RoleRepo

    override fun addRole(role: Role): UUID {
        return roleRepo.save(role).id
    }

    override fun getAllRoles(): Iterable<Role> {
        return roleRepo.findAll()
    }
}
