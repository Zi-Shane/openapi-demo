package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.dto.TradeRecord;

import java.util.List;

@Mapper
public interface TradeHistoryMapper {
    List<TradeRecord> getTradeHistory(@Param("username") String username);
}
