package com.cabit.Cab_It.dao.impl.mysql;

import com.cabit.Cab_It.dao.AdvertisementDao;
import com.cabit.Cab_It.database.mysql.DaoSetupMysqlImpl;
import com.cabit.Cab_It.database.mysql.Query;
import com.cabit.Cab_It.helper.DateTimeHelper;
import com.cabit.Cab_It.helper.ImageProcessingHelper;
import com.cabit.Cab_It.helper.ResultSetToModelConvertHelper;
import com.cabit.Cab_It.helper.SequenceIdGenerateHelper;
import com.cabit.Cab_It.model.Advertisement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class AdvertisementDaoMysqlImpl extends DaoSetupMysqlImpl implements AdvertisementDao
{
    /*
     * Advertisement dao mysql class for handle every advertisement related CRUD operations
     * */
    private SequenceIdGenerateHelper sequenceIdGenerateHelper;
    private ResultSetToModelConvertHelper resultSetToModelConvertHelper;
    private DateTimeHelper dateTimeHelper;
    private ImageProcessingHelper imageProcessingHelper;

    public AdvertisementDaoMysqlImpl()
    {
        this.sequenceIdGenerateHelper = new SequenceIdGenerateHelper();
        this.resultSetToModelConvertHelper = new ResultSetToModelConvertHelper();
        this.dateTimeHelper = new DateTimeHelper();
        this.imageProcessingHelper = new ImageProcessingHelper();
    }

    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) {
        try
        {
            int nextId = getNextId();
            String id = sequenceIdGenerateHelper.generateAdvertisementId(nextId);
            LocalDateTime dateTime = dateTimeHelper.getDateTime();

            advertisement.setId(id);
            advertisement.setIntroducedDateTime(dateTime);

            File photoFile = imageProcessingHelper.toFile(advertisement.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            PreparedStatement statement = connection.prepareStatement(Query.DML.INSERT_ADVERTISEMENT.getQuery());

            statement.setString(1, advertisement.getId());
            statement.setTimestamp(2, Timestamp.valueOf(advertisement.getIntroducedDateTime()));
            statement.setString(3, advertisement.getContent());
            statement.setBlob(4, photoInputStream);

            statement.executeUpdate();

            return advertisement;
        }
        catch (SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Advertisement setAdvertisement(Advertisement advertisement) {
        try
        {
            PreparedStatement statement = connection.prepareStatement(Query.DML.UPDATE_ADVERTISEMENT.getQuery());

            File photoFile = imageProcessingHelper.toFile(advertisement.getPhoto());
            FileInputStream photoInputStream = new FileInputStream(photoFile);

            statement.setString(1, advertisement.getContent());
            statement.setBlob(2, photoInputStream);
            statement.setString(3, advertisement.getId());

            statement.executeUpdate();

            return advertisement;
        }
        catch (SQLException | IOException exception)
        {
            loginHelper.fatal(exception.getMessage());
        }
        return null;
    }

    @Override
    public Advertisement deleteAdvertisement(Advertisement advertisement) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(Query.DML.DELETE_ADVERTISEMENT.getQuery(),
                    advertisement.getId()
            ));

            return advertisement;
        }
        catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Advertisement> getAdvertisements() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query.DML.SELECT_ADVERTISEMENTS.getQuery());

            return resultSetToModelConvertHelper.toAdvertisements(resultSet);
        } catch (SQLException sqlException)
        {
            loginHelper.fatal(sqlException.getMessage());
        }
        return null;
    }
}
