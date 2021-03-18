package com.cts.MovieCruiser;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ro_id")
int roleId;

@Column(name="ro_name")
String roleName;

@ManyToMany(mappedBy = "roleList")
private Set<User> userList;

public int getRoleId() {
return roleId;
}

public void setRoleId(int roleId) {
this.roleId = roleId;
}

public String getRoleName() {
return roleName;
}

public void setRoleName(String roleName) {
this.roleName = roleName;
}

public Set<User> getUserList() {
return userList;
}

public void setUserList(Set<User> userList) {
this.userList = userList;
}

@Override
public String toString() {
return "Role [roleId=" + roleId + ", roleName=" + roleName + ", userList=" + userList + "]";
}

}
