# shiro
RBAC �ǻ��ڽ�ɫ�ķ��ʿ��ƣ�Role-Based Access Control ��\
�� RBAC �У�Ȩ�����ɫ��������û�ͨ����Ϊ�ʵ���ɫ�ĳ�Ա���õ���Щ��ɫ��Ȩ�ޡ�\
��ͼ���ؼ���Ȩ�޵Ĺ������������ǲ㼶�໥�����ģ�Ȩ�޸������ɫ�����ѽ�ɫ�ָ����û���������Ȩ����ƺ���������������ܷ��㡣
## What is Apache Shiro?
Apache Shiro��һ������ǿ�����ģ���Դ�İ�ȫ��ܡ������Ըɾ�����ش��������֤����Ȩ����ҵ�Ự����ͼ��ܡ�\
Apache Shiro����ҪĿ��������ʹ�ú���⡣��ȫͨ���ܸ��ӣ��������˸е���ʹ�࣬����Shiroȴ���������ӵġ�\
һ���õİ�ȫ���Ӧ�� ���θ����ԣ����Ⱪ¶�򵥡�ֱ�۵�API�����򻯿�����Աʵ��Ӧ�ó���ȫ�����ѵ�ʱ��;�����

## Shiro����ʲô�أ�
1. ��֤�û����
2. �û�����Ȩ�޿��ƣ����磺1���ж��û��Ƿ������һ���İ�ȫ��ɫ��2���ж��û��Ƿ��������ĳ��������Ȩ��
3. �ڷ� web �� EJB �����Ļ����¿�������ʹ��Session API
4. ������Ӧ��֤�����ʿ��ƣ����� Session ���������з������¼�
5. �ɽ�һ���������û���ȫ����Դ������ϳ�һ�����ϵ��û� ��view��(��ͼ)
6. ֧�ֵ����¼(SSO)����
7. ֧���ṩ��Remember Me�����񣬻�ȡ�û�������Ϣ�������¼\
   ��
�ȵ�---�����ɵ�һ����������������ʹ�õ�API��

## Apache Shiro Features ����
Authentication����֤��, Authorization����Ȩ��, Session Management���Ự����, Cryptography�����ܣ�
�� Shiro ��ܵĿ����Ŷӳ�֮ΪӦ�ð�ȫ���Ĵ��ʯ: 
1. **Authentication����֤����**�û����ʶ��ͨ������Ϊ�û�����¼��
2. **Authorization����Ȩ����**���ʿ��ơ�����ĳ���û��Ƿ����ĳ��������ʹ��Ȩ�ޡ�
3. **Session Management���Ự������**�ض����û��ĻỰ����,�����ڷ�web �� EJB Ӧ�ó���
4. **Cryptography�����ܣ���**�ڶ�����Դʹ�ü����㷨���ܵ�ͬʱ����֤����ʹ�á�

���������Ĺ�����֧�ֺͼ�ǿ��Щ��ͬӦ�û����°�ȫ����Ĺ�ע��:
1. Web֧�֣�Shiro �ṩ�� web ֧�� api �����Ժ����ɵı��� web Ӧ�ó���İ�ȫ��
2. ���棺������ Apache Shiro ��֤��ȫ�������١���Ч����Ҫ�ֶΡ�
3. ������Apache Shiro ֧�ֶ��߳�Ӧ�ó���Ĳ������ԡ�
4. ���ԣ�֧�ֵ�Ԫ���Ժͼ��ɲ��ԣ�ȷ�������Ԥ���һ����ȫ��
5. ��Run As����������������û�������һ���û������(����ɵ�ǰ����)��
6. ��Remember Me������ session ��¼�û�����ݣ�ֻ����ǿ����Ҫʱ����Ҫ��¼��

## High-Level Overview �߼�����
�ڸ���㣬Shiro �ܹ�����������Ҫ�����Subject,SecurityManager�� Realm:
1. Subject����ǰ�û���Subject ������һ���ˣ���Ҳ�����ǵ����������ػ������ʻ���ʱ���ػ�������������C��ǰ������������κ��¼���
2. SecurityManager����������Subject��SecurityManager �� Shiro �ܹ��ĺ��ģ�����ڲ���ȫ�����ͬ��ɰ�ȫɡ��
3. **Realms�����ڽ���Ȩ����Ϣ����֤�������Լ�ʵ��**��\
Realm ��������һ���ض��İ�ȫ DAO������װ������Դ���ӵ�ϸ�ڣ��õ�Shiro �������ص����ݡ�\
������ Shiro ��ʱ�������ָ������һ��Realm ��ʵ����֤��authentication����/�� ��Ȩ��authorization����

������Ҫʵ��Realms��Authentication �� Authorization: 
Authentication ��������֤�û����;
Authorization ����Ȩ���ʿ��ƣ����ڶ��û����еĲ�����Ȩ��֤�����û��Ƿ�������е�ǰ�����������ĳ�����ӣ�ĳ����Դ�ļ��ȡ�

#### �û���User
```
Long uid;
String username;
String password;
String salt;//�����������
byte state;//�û�״̬,0:����δ��֤������û�м��û��������֤��ȵȣ�--�ȴ���֤���û� , 1:����״̬,2���û�������.
...
List<SysRole> roleList;//һ���û���Ӧ�����ɫ
```

#### �û��� �� ��ɫ�� ֮��Ķ�Զ������ Sys_User_Role �� ����
user uid
role rid
#### ��ɫ��SysRole
```
Integer rid; // ���
String role; // ��ɫ��ʶ�������ж�ʹ��,��"admin",�����Ψһ��:
String description; // ��ɫ����,UI������ʾʹ��
Boolean available = Boolean.FALSE; // �Ƿ����,��������ý�������Ӹ��û�

List<SysPermission> permissions;// һ����ɫ ��� Ȩ��
List<User> userInfos;//�û� - ��ɫ��ϵ����; һ����ɫ��Ӧ����û�
```

`
rid available  description  role
1   0           ����Ա        admin
2   0           VIP��Ա        vip
3   1           test         test
`

#### ��ɫ�� �� Ȩ�ޱ� ֮��Ķ�Զ������ Sys_Role_Permission �� ����
role rid\
permission pid
#### Ȩ�ޱ�SysPermission
```
Integer pid;//����.
String name;//����.
String resourceType;//��Դ���ͣ�[menu|button]
String url;//��Դ·��.
String permission; //Ȩ���ַ���,menu���ӣ�role:*��button���ӣ�role:create,role:update,role:delete,role:view
Long parentId; //�����
String parentIds; //������б�
Boolean available = Boolean.FALSE;// �Ƿ����,��������ý�������Ӹ��û�

List<SysRole> roles;//һ�� Ȩ�� �����ɫ��Ȩ�� �� ��ɫ ��ϵ ��Զ�
```
```
pid available  name    parentId parentIds   permission resourceType     url
1    0        �û�����     0        0/        user:view    menu         user/list
2    0        �û����     1        0/1       user:add     button       user/add
3    0        �û�ɾ��     1        0/1       user:del     button       user/de
```

���� �û��� ��ѯ�û���ɫ��Ȩ�ޣ�ȷ���书��Ȩ��

����һ���û����ж�ӵ��ʲô��ɫ��Ȩ�ޣ���������Ӧ��ɫ����ֱ�Ӹ���Ȩ��

