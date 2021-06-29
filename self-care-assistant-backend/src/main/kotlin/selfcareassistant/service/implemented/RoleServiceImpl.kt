package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.RoleEntity
import selfcareassistant.repository.RoleRepo
import selfcareassistant.service.RoleService
import java.util.*

@Service
class RoleServiceImpl: RoleService {

    @Autowired
    lateinit var roleRepo: RoleRepo

    override fun addRole(roleEntity: RoleEntity): UUID {
        return roleRepo.save(roleEntity).id!!
    }

    override fun getAllRoles(): Iterable<RoleEntity> {
        return roleRepo.findAll()
    }

    override fun deleteRole(id: UUID): Boolean {
        if(!roleRepo.findById(id).isPresent()) {
            return false
        }

        roleRepo.deleteById(id)
        return true
    }

    override fun changeRole(roleEntity: RoleEntity): Boolean {
        var flag = false
        if(roleRepo.findById(roleEntity.id!!).isPresent) {
            flag = true
        }

        roleRepo.save(roleEntity)
        return flag
    }
}
