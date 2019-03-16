package com.pichub.hello.service;

import com.pichub.hello.bo.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumService {
    Album getAlbum();
    List<Album> listAlbum();
    Album createAlbum();
    Album deleteAlbum();
}
