package cn.arorms.sddanalyzer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import cn.arorms.sddanalyzer.entity.ResultEntity;
import java.util.List;

@Mapper
public interface ResultMapper {
    @Select("SELECT * FROM res")
    @Results({
            @Result(column = "fig_id", property = "figId"),
            @Result(column = "res_fig", property = "resFig")
    })
    List<ResultEntity> getAllResults();

    @Select("SELECT * FROM res WHERE fig_id = #{figId}")
    ResultEntity getResultById(int figId);
}
