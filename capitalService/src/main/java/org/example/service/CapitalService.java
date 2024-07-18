package org.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.model.Capital;
import org.example.repository.CapitalDao;
import org.springframework.stereotype.Service;

@Service
public class CapitalService extends ServiceImpl<CapitalDao, Capital> {


}
