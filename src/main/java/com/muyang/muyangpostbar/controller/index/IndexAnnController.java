package com.muyang.muyangpostbar.controller.index;

import com.muyang.muyangpostbar.pojo.Ann;
import com.muyang.muyangpostbar.service.AnnService;
import com.muyang.muyangpostbar.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-27 14:18:41
 */
@RestController
@RequestMapping("/index/ann")
public class IndexAnnController {
    @Autowired
    private AnnService annService;

    @GetMapping("/getAnn")
    public Result getAnn(){
        List<Ann> result = annService.getAnn();
        return Result.success(result);
    }
}
