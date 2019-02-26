package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.administration.User;
import cl.grupo.maldonado.app.basket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;



    /**
     *
     * @param user
     */
    public void signUp(User user){
        user.setPassword( encoder.encode(user.getPassword()) );
        repository.save(user);
    }

    public List<User> listAll(){
        return repository.findAll();
    }

    public User findById( Integer oid ){

        return repository.findById( oid).get();
    }

    public void deleteById( Integer oid ){

        repository.delete( new User(oid) );
    }

    public void save(User user){
        signUp(user);
    }

}
