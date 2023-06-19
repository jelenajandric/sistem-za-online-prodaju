package org.unibl.etf.webshopapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etf.webshopapplication.model.entities.ClientEntity;


@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    ClientEntity findClientEntityByUsername(String username);

    @Modifying
    @Transactional
    @Query("update ClientEntity c set c.name=:name, c.surname=:surname, c.password=:password, c.city=:city," +
            " c.email=:email where c.username=:username")
    int updateClientByUsername(@Param("name") String name, @Param("surname") String surname,
                               @Param("password") String password, @Param("city") String city,
                               @Param("email") String email, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("update ClientEntity c set c.avatar=:avatar where c.username=:username")
    void updateImage(@Param("avatar") String avatar, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("update ClientEntity  c set c.isActivated = true where c.username=:username")
    void activateClientAccount(@Param("username") String username);

    @Query("select c.id from ClientEntity c where c.username =:username")
    int getClientIdByUsername(@Param("username") String username);

    @Query("select c.username from ClientEntity c where c.id =:id")
    String getClientUsernameById(@Param("id") int id);

    @Query("select c from ClientEntity c where c.id=:id")
    ClientEntity findClientEntityById(@Param("id") int id);

    @Query("select c.avatar from ClientEntity c where c.username =:username")
    String findAvatarByUsername(@Param("username") String username);

    @Query("select c.email from ClientEntity c where c.username =:username")
    String findEmailByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("update ClientEntity c set c.email=:email where c.username=:username")
    void updateEmail(@Param("email") String email, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("update ClientEntity c set c.avatar=NULL where c.username=:username")
    void deleteAvatarPhoto(@Param("username") String username);
}
