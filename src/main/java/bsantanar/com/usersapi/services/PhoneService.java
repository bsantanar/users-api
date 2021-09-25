package bsantanar.com.usersapi.services;

import bsantanar.com.usersapi.models.Phone;
import bsantanar.com.usersapi.models.User;

public interface PhoneService {

    Phone updateUserPhone(Phone phone, User user);

}
