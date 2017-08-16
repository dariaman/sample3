package sample3.Controllers;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sample3.models.User;
import sample3.services.UserService;

@RestController
public class UserController {
    UserService userService;
    
    @RequestMapping(value = "/test/", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody List<User> getListUser(){
        List<User> users = userService.getListUser();
        return users;
    }
    
    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public @ResponseBody User add(@RequestBody User user ){
        userService.saveOrUpdate(user);
        return user;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public @ResponseBody User update(@PathVariable("id") int id, @RequestBody User user){
        user.setId(id);
        userService.saveOrUpdate(user);
        return user;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody User delete(@PathVariable("id") int id){
        User user = userService.findUserById(id);
        userService.deleteUser(id);
        return user;
    }
}
