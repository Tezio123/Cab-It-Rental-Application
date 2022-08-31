package com.cabit.Cab_It.service;

import com.cabit.Cab_It.dao.impl.mysql.AdvertisementDaoMysqlImpl;
import com.cabit.Cab_It.helper.ImageProcessingHelper;
import com.cabit.Cab_It.model.Advertisement;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvertisementService
{
    /*
     * Service class to perform services related to a advertisement
     * */
    private AdvertisementDaoMysqlImpl advertisementDaoMysql;
    private ImageProcessingHelper imageProcessingHelper;

    public static Map<String, Advertisement> advertisementMap;

    public AdvertisementService()
    {
        this.advertisementDaoMysql = new AdvertisementDaoMysqlImpl();
        this.imageProcessingHelper = new ImageProcessingHelper();
    }

    public void refreshAdvertisementMap()
    {
        advertisementMap = new HashMap<>();
        List<Advertisement> advertisements = getAdvertisements();

        for(Advertisement advertisement : advertisements)
            advertisementMap.put(advertisement.getId(), advertisement);
    }

    public Advertisement addAdvertisement(String content, File photoFile)
    {
        byte[] compressedPhoto = imageProcessingHelper.preprocess(photoFile);

        Advertisement advertisement = new Advertisement(
                null,
                content,
                compressedPhoto
        );

        advertisement = advertisementDaoMysql.addAdvertisement(advertisement);
        advertisement.setPhoto(imageProcessingHelper.decompressBytes(advertisement.getPhoto()));

        advertisementMap.put(advertisement.getId(), advertisement);

        return advertisement;
    }

    public Advertisement setAdvertisement(String id, String content, File photoFile)
    {
        Advertisement saved = advertisementMap.get(id);

        byte[] photo = imageProcessingHelper.compressBytes(saved.getPhoto());

        if(photoFile != null)
            photo = imageProcessingHelper.preprocess(photoFile);

        Advertisement advertisement = new Advertisement(
                id,
                saved.getIntroducedDateTime(),
                content,
                photo
        );

        advertisement = advertisementDaoMysql.setAdvertisement(advertisement);
        advertisement.setPhoto(imageProcessingHelper.decompressBytes(advertisement.getPhoto()));

        advertisementMap.put(advertisement.getId(), advertisement);

        return advertisement;
    }

    public Advertisement deleteAdvertisement(String id)
    {
        Advertisement advertisement = advertisementMap.remove(id);
        Advertisement deleted =  advertisementDaoMysql.deleteAdvertisement(advertisement);

        if(deleted == null)
            advertisementMap.put(advertisement.getId(), advertisement);

        return deleted;
    }

    public List<Advertisement> getAdvertisements()
    {
        return advertisementDaoMysql.getAdvertisements();
    }

    public Map<String, Advertisement> getAdvertisementMap()
    {
        return advertisementMap;
    }
}
