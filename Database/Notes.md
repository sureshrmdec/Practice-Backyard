Q: Difference between char, varchar, nchar, nvarchar ?
A: char is fixed size datatype, so if you define char and don't use the complete
   size,it will be wasted but that's not the case with varchar which is variable size
   datatype +2 bit of storage information. nchar and nvarchar are counterpart with
   twice the size for multilangual support

Q: Difference between where and having clause in SQL- group by comparison
A: where condition is used to fetch the data, so row which don't satisfy the condition
   are not fetched, while having is used to summarize the data

   Where clause is used for filtering rows and it applies to each and every row while
   having clause is used to filter groups in SQL

Q: Difference between primary key and foriegn key in database ?
A:

Q: Define referential integrity in database ?
A: For every foriegn key values there should be a primary key associated otherwise it
   will violate referential integrity

SQL Basics :
    Data Definition Language : Create table, create index, create view, alter table,drop table,
    create schema, default, not null, check, primary key, foreign key
    Data Manupulation language : select, insert,where, group by ,having update,delete, rollback, commit
    comparison Operator : = < > >= <=
    Logical Operator : AND OR not
    Special Opertor : between, IN, EXITS, LIKE ,DISTINCT, IS null
    Aggregate function : Count, MAX, MIN, SUM,AVG

    Create table tablename {
    colName data type constraint
    }

    composite primary key : primary key (recipe_id, ingredient_id);
    On update cascade : mean if you update main table key which other table uses
    as it's foreign key then it will also be updated.

    SQL Constraint : Not null, Unique, default, check

    Index : Create index <indexName> on <tableName>(<colName1>, <colName2>);
    Insert : Insert into <tableName> values ();
    Select : Select *(all columns) from <tableName>;
    Update : update <tableName> set <colName> = 'value';

    Alter: Alter table <tableName> add  <columnName> /to modify and also to add Constraint
    Alter table <tableName> modify(<ColName(datatype)>);
    Alter table <tableName> drop <columnName>;
    alter table add constriant <ConstraintName<columnName(datatype)>>


    Create table <tableName> as Select * from emp;

    Groupby : to group and then use with aggregation function to find min and MAX
    in that group with having and order by summarization
