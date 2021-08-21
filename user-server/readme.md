# shiro
RBAC 是基于角色的访问控制（Role-Based Access Control ）\
在 RBAC 中，权限与角色相关联，用户通过成为适当角色的成员而得到这些角色的权限。\
这就极大地简化了权限的管理。这样管理都是层级相互依赖的，权限赋予给角色，而把角色又赋予用户，这样的权限设计很清楚，管理起来很方便。
## What is Apache Shiro?
Apache Shiro是一个功能强大、灵活的，开源的安全框架。它可以干净利落地处理身份验证、授权、企业会话管理和加密。\
Apache Shiro的首要目标是易于使用和理解。安全通常很复杂，甚至让人感到很痛苦，但是Shiro却不是这样子的。\
一个好的安全框架应该 屏蔽复杂性，向外暴露简单、直观的API，来简化开发人员实现应用程序安全所花费的时间和精力。

## Shiro能做什么呢？
1. 验证用户身份
2. 用户访问权限控制，比如：1、判断用户是否分配了一定的安全角色。2、判断用户是否被授予完成某个操作的权限
3. 在非 web 或 EJB 容器的环境下可以任意使用Session API
4. 可以响应认证、访问控制，或者 Session 生命周期中发生的事件
5. 可将一个或以上用户安全数据源数据组合成一个复合的用户 “view”(视图)
6. 支持单点登录(SSO)功能
7. 支持提供“Remember Me”服务，获取用户关联信息而无需登录\
   …
等等---都集成到一个有凝聚力的易于使用的API。

## Apache Shiro Features 特性
Authentication（认证）, Authorization（授权）, Session Management（会话管理）, Cryptography（加密）
被 Shiro 框架的开发团队称之为应用安全的四大基石: 
1. **Authentication（认证）：**用户身份识别，通常被称为用户“登录”
2. **Authorization（授权）：**访问控制。比如某个用户是否具有某个操作的使用权限。
3. **Session Management（会话管理）：**特定于用户的会话管理,甚至在非web 或 EJB 应用程序。
4. **Cryptography（加密）：**在对数据源使用加密算法加密的同时，保证易于使用。

还有其他的功能来支持和加强这些不同应用环境下安全领域的关注点:
1. Web支持：Shiro 提供的 web 支持 api ，可以很轻松的保护 web 应用程序的安全。
2. 缓存：缓存是 Apache Shiro 保证安全操作快速、高效的重要手段。
3. 并发：Apache Shiro 支持多线程应用程序的并发特性。
4. 测试：支持单元测试和集成测试，确保代码和预想的一样安全。
5. “Run As”：这个功能允许用户假设另一个用户的身份(在许可的前提下)。
6. “Remember Me”：跨 session 记录用户的身份，只有在强制需要时才需要登录。

## High-Level Overview 高级概述
在概念层，Shiro 架构包含三个主要的理念：Subject,SecurityManager和 Realm:
1. Subject：当前用户，Subject 可以是一个人，但也可以是第三方服务、守护进程帐户、时钟守护任务或者其它–当前和软件交互的任何事件。
2. SecurityManager：管理所有Subject，SecurityManager 是 Shiro 架构的核心，配合内部安全组件共同组成安全伞。
3. **Realms：用于进行权限信息的验证，我们自己实现**。\
Realm 本质上是一个特定的安全 DAO：它封装与数据源连接的细节，得到Shiro 所需的相关的数据。\
在配置 Shiro 的时候，你必须指定至少一个Realm 来实现认证（authentication）和/或 授权（authorization）。

我们需要实现Realms的Authentication 和 Authorization: 
Authentication 是用来验证用户身份;
Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。

#### 用户表：User
```
Long uid;
String username;
String password;
String salt;//加密密码的盐
byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
...
List<SysRole> roleList;//一个用户对应多个角色
```

#### 用户表 与 角色表 之间的多对多关联用 Sys_User_Role 表 关联
user uid
role rid
#### 角色表：SysRole
```
Integer rid; // 编号
String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
String description; // 角色描述,UI界面显示使用
Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

List<SysPermission> permissions;// 一个角色 多个 权限
List<User> userInfos;//用户 - 角色关系定义; 一个角色对应多个用户
```

`
rid available  description  role
1   0           管理员        admin
2   0           VIP会员        vip
3   1           test         test
`

#### 角色表 与 权限表 之间的多对多关联用 Sys_Role_Permission 表 关联
role rid\
permission pid
#### 权限表：SysPermission
```
Integer pid;//主键.
String name;//名称.
String resourceType;//资源类型，[menu|button]
String url;//资源路径.
String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
Long parentId; //父编号
String parentIds; //父编号列表
Boolean available = Boolean.FALSE;// 是否可用,如果不可用将不会添加给用户

List<SysRole> roles;//一个 权限 多个角色；权限 和 角色 关系 多对多
```
```
pid available  name    parentId parentIds   permission resourceType     url
1    0        用户管理     0        0/        user:view    menu         user/list
2    0        用户添加     1        0/1       user:add     button       user/add
3    0        用户删除     1        0/1       user:del     button       user/de
```

根据 用户名 查询用户角色、权限，确认其功能权限

新增一个用户，判断拥有什么角色、权限，并给予相应角色，不直接给予权限

