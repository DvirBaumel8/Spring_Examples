import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class MainConfiguration {
    @Bean
    public EncryptorsFactory encryptCreatorFactory() {
        EncryptorsFactory factory = new EncryptorsFactory();
        Map<EncryptMethod, Encrypt> encrypts = new EnumMap<>(EncryptMethod.class);
        encrypts.put(EncryptMethod.REVERSE, new ReverseEncrypt());
        encrypts.put(EncryptMethod.REVERSE_SKIP_FIRST, new ReverseSkipFirstEncrypt());
        encrypts.put(EncryptMethod.SWITCH_FIRST_LAST, new SwitchLastFirstEncrypt());
        factory.setEncryptsMap(encrypts);
        return factory;
    }

    @Bean
    public RegisterManager registerManager() {
        return new RegisterManager(usersRepository(), encryptCreatorFactory());
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepository();
    }
}
