
## pagehelper��ҳ�������
```yaml
#��ʶ����һ�����ݿ�
pagehelper.helperDialect=mysql
#���ú����������pageNum<1���ѯ��һҳ�����pageNum>pages���ѯ���һҳ
pagehelper.reasonable=true
#Ϊ��֧��startPage(Object params)�����������˸ò��������ò���ӳ�䣬���ڴӶ����и���������ȡֵ�� ��������pageNum,pageSize,count,pageSizeZero,reasonable��������ӳ�����Ĭ��ֵ�� Ĭ��ֵΪpageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero
pagehelper.params=count=countSql
#֧��ͨ�� Mapper �ӿڲ��������ݷ�ҳ������Ĭ��ֵfalse����ҳ�����Ӳ�ѯ�����Ĳ���ֵ�У��Զ��������� params ���õ��ֶ���ȡֵ�����ҵ����ʵ�ֵʱ�ͻ��Զ���ҳ
pagehelper.supportMethodsArguments=true
#��� pageSize=0 �ͻ��ѯ��ȫ���Ľ�����൱��û��ִ�з�ҳ��ѯ��
pagehelper.page-size-zero=true
```