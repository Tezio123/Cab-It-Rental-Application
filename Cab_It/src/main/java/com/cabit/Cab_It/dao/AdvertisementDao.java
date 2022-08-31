package com.cabit.Cab_It.dao;

import com.cabit.Cab_It.model.Advertisement;

import java.util.List;

public interface AdvertisementDao
{
    Advertisement addAdvertisement(Advertisement advertisement);

    Advertisement setAdvertisement(Advertisement advertisement);

    Advertisement deleteAdvertisement(Advertisement advertisement);

    List<Advertisement> getAdvertisements();
}
