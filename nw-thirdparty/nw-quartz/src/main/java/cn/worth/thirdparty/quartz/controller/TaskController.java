package cn.worth.thirdparty.quartz.controller;

import cn.worth.common.v2.annotation.CurrentUser;
import cn.worth.common.v2.constant.CommonConstant;
import cn.worth.common.controller.BaseController;
import cn.worth.common.pojo.R;
import cn.worth.common.utils.StringUtils;
import cn.worth.common.v2.domain.LoginUser;
import cn.worth.thirdparty.quartz.domain.Task;
import cn.worth.thirdparty.quartz.service.ITaskService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenxiaoqing
 * @since 2019-09-09
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController<ITaskService, Task>  {

    @Autowired
    private ITaskService taskService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return Task
     */
    @GetMapping("/{id}")
    public R<Task> get(@PathVariable Long id) {
        return new R<>(taskService.selectById(id));
    }


    /**
     * 分页查询信息
     *
     * @return 分页对象
     */
    @RequestMapping("/page")
    public R page(Page<Task> entityPage, Task entity) {
        EntityWrapper<Task> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", CommonConstant.STATUS_NORMAL);
        String jobName = entity.getJobName();
        if(StringUtils.isNotBlank(jobName)){
            wrapper.like("job_name", jobName);
        }
        String jobGroup = entity.getJobGroup();
        if(StringUtils.isNotBlank(jobGroup)){
            wrapper.eq("job_group", jobGroup);
        }
        String jobStatus = entity.getJobStatus();
        if(StringUtils.isNotBlank(jobStatus)){
            wrapper.eq("job_status", jobStatus);
        }
        wrapper.orderBy("id");

        Page<Task> page = selectPage(entityPage, wrapper);
        return R.success(page);
    }

    /**
     * 添加
     *
     * @param task 实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody Task task) {
        return new R<>(taskService.insert(task));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id, @CurrentUser LoginUser loginUser) {
        return new R<>(taskService.remove(id, loginUser.getId()));
    }

    /**
     * 编辑
     *
     * @param task 实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody Task task, @CurrentUser LoginUser loginUser) throws SchedulerException {
        return new R<>(taskService.updateTask(task, loginUser.getId()));
    }

    @PostMapping("batchRemove")
    public R<Boolean> batchRemove(@RequestBody Long[] ids, @CurrentUser LoginUser loginUser) throws SchedulerException {
        return new R<>(taskService.batchRemove(ids, loginUser.getId()));
    }
    @PostMapping("changeStatus")
    public R<Boolean> changeStatus(Long jobId, String status, @CurrentUser LoginUser loginUser) throws SchedulerException {
        return new R<>(taskService.changeStatus(jobId, status, loginUser.getId()));
    }

    @PostMapping("updateCron")
    public R<Boolean> updateCron(Long jobId, String cron, @CurrentUser LoginUser loginUser) throws SchedulerException {
        return new R<>(taskService.updateCron(jobId, cron, loginUser.getId()));
    }
}
