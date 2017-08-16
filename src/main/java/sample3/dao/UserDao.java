/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample3.dao;

import java.util.List;
import sample3.models.User;

/**
 *
 * @author dariaman.siagian
 */
public interface UserDao {
    public List<User> getListUser();    
    public void saveOrUpdate(User user);
    public void deleteUser(int id);
    public User  findUserById(int id);
}
