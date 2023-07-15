package collections.data;

import collections.security.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {
    AppUser findByUsername(String username);

    @Transactional
    AppUser findById(int id);

    AppUser create(AppUser user);
}
