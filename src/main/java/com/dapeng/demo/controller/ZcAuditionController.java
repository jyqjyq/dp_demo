package com.dapeng.demo.controller;

import com.dapeng.demo.models.dto.AuditionCourseDetailDTO;
import com.dapeng.demo.models.dto.AuditionCourseSummaryDTO;
import com.dapeng.demo.models.param.AuditionCourseListParam;
import com.dapeng.demo.models.param.AuditionCourseParam;
import com.dapeng.demo.models.param.AuditionCourseSummaryParam;
import com.dapeng.demo.models.resp.AuditionCourseListResp;
import com.dapeng.demo.models.resp.AuditionCourseSummaryResp;
import com.dapeng.demo.service.AuditionCourseService;
import com.dapeng.demo.util.paging.PageResult;
import com.dapeng.demo.util.response.ApiResult;
import com.dapeng.demo.util.utils.DateUtils;
import com.dapeng.demo.util.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: AuditionController
 * @Description:试听课接口类
 * @author: JiaYongQing
 * @date: 2019年11月29日
 */
@Slf4j
@Api(value = "ZcAuditionController", description = "试学课相关API")
@RestController
@RequestMapping("/api/zc/audition")
public class ZcAuditionController {

    @Autowired
    private AuditionCourseService auditionCourseService;

    /**
     * @throws
     * @Title: getCourseList
     * @Description: 根据日期，学院 获取课程列表
     * @author: JiaYongQing
     * @param: @param param
     * @param: @return
     * @return: ApiResult<List < String>>
     */
    @PostMapping("/getCourseList")
    public ApiResult<List<String>> getCourseList(@RequestBody @Valid AuditionCourseParam param) {
        log.info("getCourseList param:{}", JsonUtils.toJSON(param));
        Date paramDate = DateUtils.str2Date(param.getDate(), DateUtils.DF_yyyy_MM_dd);
        return ApiResult.ok(auditionCourseService.getCourseList(paramDate, param.getCollege()));
    }

    /**
     * @throws
     * @Title: getSummary
     * @Description: 获取 /getAuditionList 接口的 统计信息
     * @author: JiaYongQing
     * @param: @param param
     * @param: @return
     * @return: ApiResult<AuditionCourseSummaryResp>
     */
    @PostMapping("/getSummary")
    public ApiResult<AuditionCourseSummaryResp> getSummary(@RequestBody @Valid AuditionCourseSummaryParam param) {
        log.info("getSummary param:{}", JsonUtils.toJSON(param));
        Date paramDate = DateUtils.str2Date(param.getDate(), DateUtils.DF_yyyy_MM_dd);

        AuditionCourseSummaryDTO auditionCourseSummaryDTO = auditionCourseService.getSummary(paramDate, param.getClassCodes(), param.getCourseName());
        AuditionCourseSummaryResp resp = new AuditionCourseSummaryResp();
        BeanUtils.copyProperties(resp, auditionCourseSummaryDTO);
        return ApiResult.ok(resp);
    }


    /**
     * @throws
     * @Title: getAuditionList
     * @Description: 获取试听课列表
     * @author: JiaYongQing
     * @param: @param param
     * @param: @return
     * @return: ApiResult<PageResult < AuditionCourseListResp>>
     */
    @PostMapping("/getAuditionList")
    public ApiResult<PageResult<AuditionCourseListResp>> getAuditionList(@RequestBody @Valid AuditionCourseListParam param) {
        log.info("getAuditionList param:{}", JsonUtils.toJSON(param));
        Date paramDate = DateUtils.str2Date(param.getDate(), DateUtils.DF_yyyy_MM_dd);

        PageHelper.startPage(param.getPageNumber(), param.getPageSize());
        List<AuditionCourseDetailDTO> detailsList = auditionCourseService.getAuditionCourseDetails(paramDate, param.getClassCodes(), param.getCourseName(), param.getSortType());
        PageInfo<AuditionCourseDetailDTO> pagereInfo = new PageInfo<>(detailsList);

        List<AuditionCourseListResp> retList = new ArrayList<>();

        detailsList.forEach(aDto -> {
            AuditionCourseListResp aResp = new AuditionCourseListResp();
            BeanUtils.copyProperties(aResp, aDto);
            retList.add(aResp);
        });

        return ApiResult.ofPage(retList, pagereInfo.getTotal());
    }


}
