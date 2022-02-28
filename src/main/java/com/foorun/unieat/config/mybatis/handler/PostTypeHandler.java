package com.foorun.unieat.config.mybatis.handler;

import com.foorun.unieat.domain.common.PostType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostTypeHandler extends BaseTypeHandler<PostType> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PostType postType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, postType.ordinal());
    }

    @Override
    public PostType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return PostType.indexOf(resultSet.getInt(s));
    }

    @Override
    public PostType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return PostType.indexOf(resultSet.getInt(i));
    }

    @Override
    public PostType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return PostType.indexOf(callableStatement.getInt(i));
    }
}
