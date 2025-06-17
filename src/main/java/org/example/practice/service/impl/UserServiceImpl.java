package org.example.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.practice.entity.User;
import org.example.practice.exception.UserRegisterException;
import org.example.practice.mapper.IUserMapper;
import org.example.practice.service.IUserService;
import org.example.practice.util.BoolmFilterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper userMapper;


    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return userMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void register(User user){
        String username = user.getUsername();
       boolean canRegister = BoolmFilterUtil.getInstance().contains(username);
        if(canRegister) throw new UserRegisterException("用户注册异常");
            user.setUpdateby("admin");
             user.setUpdateTime(new Date());
            addUser(user);
            BoolmFilterUtil.getInstance().add(username);

    }


}
