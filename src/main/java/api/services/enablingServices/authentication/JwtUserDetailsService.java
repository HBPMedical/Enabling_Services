package api.services.enablingServices.authentication;

import api.services.enablingServices.model.MipUser;
import api.services.enablingServices.repository.MipUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private MipUsersRepository mipUsersRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MipUser mipUser = mipUsersRepository.findByUsername(username);
        if (mipUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(mipUser.getUsername(), mipUser.getPassword(),
                new ArrayList<>());
    }

    public MipUser save(MipUser user) {
        MipUser newUser = new MipUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return mipUsersRepository.save(newUser);
    }

}