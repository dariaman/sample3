package sample3.Controllers;

import Crypt.TripleDESTest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static javassist.CtMethod.ConstParameter.string;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sample3.models.User;
import sample3.services.UserService;
import static sun.security.krb5.Confounder.bytes;
import org.apache.commons.codec.binary.Hex;

@RestController
public class UserController {
    UserService userService;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody List<User> getListUser(){
        List<User> users = userService.getListUser();
        return users;
//        return this.response = "";
    }
    
    @RequestMapping(value = "/tes", method=RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String EncString() throws Exception
    {
        String text = "kyle boon";

        byte[] codedtext = new TripleDESTest().encrypt(text);
//        String str = new String(codedtext, StandardCharsets.UTF_8);
        
        String decodedtext = new TripleDESTest().decrypt(codedtext);
        return JSONObject.quote(decodedtext);
    }
    
    @RequestMapping(value = "/enc/{id}", method=RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String EncString(@PathVariable("id") String id) throws Exception
    {

        byte[] codedtext = new TripleDESTest().encrypt(id);
        String str = Hex.encodeHexString(codedtext);
//        String str = new String(codedtext, StandardCharsets.UTF_8);
        
        return JSONObject.quote(str);
    }
    
    @RequestMapping(value = "/dec/{id}", method=RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String DecString(@PathVariable("id") String id) throws Exception
    {
        byte[] b = DatatypeConverter.parseHexBinary(id);
        String decodedtext = new TripleDESTest().decrypt(b);
        return JSONObject.quote(decodedtext);
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
