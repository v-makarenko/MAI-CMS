package ru.vmakarenko.dao.filters;
/**
 * Created by User on 25.02.2015.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vmakarenko.dao.common.FilterParam;
import ru.vmakarenko.dao.common.FilterType;

import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Вспомогательный класс для построения дерева join'ов. Представляет собой одну ноду.
 */
class JoinObj {
    private String join;                    // название присоединяемой сущности
    private String alias = "entity";        // ее alias
    private JoinObj parent;                 // родитель сущности, если таковой имеется
    private static long inc = 1;            // счетчик для того, чтобы не пересеклись alias'ы
    private List<JoinObj> childs
            = new ArrayList<JoinObj>();

    public List<JoinObj> getChilds() {
        return childs;
    }

    public JoinObj(String join, JoinObj parent) {
        this.join = join;
        this.parent = parent;
        if (parent == null) {
            inc = 1;
        }
    }

    public static long getInc() {
        return inc;
    }

    /**
     * Обход дерева joinов, начиная с текущего
     *
     * @param sb StringBuilder который наполняется строкой, представляющую из себя корректный join clause для будущего запроса
     */
    public void traverse(StringBuilder sb) {
        if (parent != null) {
            sb.append(" left join ");
            sb.append(parent.alias + "." + join);
            sb.append(" ");
            sb.append(alias);
            sb.append(" ");
        }
        for (JoinObj child : childs) {
            child.traverse(sb);
        }
    }

    /**
     * Получение join'a из child'овых объектов
     *
     * @param join название join'a, который необходимо получить
     * @return заправшиваемый join или же null, если таковой не найден
     */
    public JoinObj getJoin(String join) {
        for (JoinObj joinObj : childs) {
            if (joinObj.getJoin().equals(join)) {
                return joinObj;
            }
        }
        return null;
    }

    public String getJoin() {
        return join;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void addJoin(JoinObj newObj) {
        childs.add(newObj);
        inc++;
    }
}

/**
 * Общий класс для фильтров, который будет создавать из аннотированного кастомного фильтра строчки для вставки в GenericDao
 *
 * Расширяется по мере необходимости парсинга новых полей
 */
public class CommonFilter {
    private List<FilterParam> filterParams = new ArrayList<FilterParam>();
    private JoinObj rootJoin = new JoinObj("parent", null);
    private String orderDir;               // в какую сторону поиск
    private String orderColumn;            // по какой колонке
    private Logger LOG = LoggerFactory.getLogger(CommonFilter.class);
    private Long limit;
    private Long page;

    /**
     * Конструктор
     *
     * @param filter кастомный фильтр, который нужно распарсить
     */
    public CommonFilter(Object filter) {
        if (filter instanceof BasicFilter) {
            BasicFilter basicFilter = (BasicFilter) filter;
            if (basicFilter.getOrderDir() != null) {
                orderDir = basicFilter.getOrderDir().toUpperCase().equals("ASC") ? "ASC" : "DESC";
            }
            if (basicFilter.getOrderColumn() != null) {
                orderColumn = basicFilter.getOrderColumn().replace(" ", "");
            }
            limit = basicFilter.getLimit();
            page = basicFilter.getPage();
        }
        long inc = 1;
        try {
            Field[] fields = filter.getClass().getDeclaredFields(); // берем все поля фильтра
            for (Field field : fields) {
                field.setAccessible(true);  // делаем их доступными
                if (field.get(filter) == null) continue; // пустые поля нам не нужны
                if (field.get(filter).getClass().equals(String.class) && ((String) field.get(filter)).isEmpty())
                    continue; // пустые строки нам не нужны
                if ((field.getModifiers() & java.lang.reflect.Modifier.FINAL) == java.lang.reflect.Modifier.FINAL)
                    continue; // константы не нужны
                ru.vmakarenko.dao.anno.FilterParam filterParamAnno = field.getAnnotation(ru.vmakarenko.dao.anno.FilterParam.class); // берем аннотацию с поля
                if (filterParamAnno != null) {
                    FilterParam newFilterParam = new FilterParam();
                    String joinPath = !filterParamAnno.joinPath().equals("-") ? filterParamAnno.joinPath() : null;
                    String fName = !filterParamAnno.name().equals("-") ? filterParamAnno.name() : field.getName();
                    newFilterParam.setJoinName(parseJoins(joinPath != null ? joinPath + "." + fName : fName));
                    newFilterParam.setFieldName(fName);
                    newFilterParam.setFieldValue(field.get(filter));
                    newFilterParam.setParamName(fName + inc++);    // добавляем inc, чтобы не пересечься
                    newFilterParam.setFilterType(filterParamAnno.filterType());
                    filterParams.add(newFilterParam);
                }

            }
        } catch (Exception e) {
            LOG.error("Filter object cannot be parsed by CommonFilter. Maybe you should add custom parsing of new fields (see javadoc)");
            throw new RuntimeException(e);
        }


    }

    /**
     * Парсит строку, которая представляет собой путь до атрибута, включая в себя само имя атрибута, а также все join'ы, которые встречаются по пути
     *
     * @param s строка, представляющая собой путь до атрибута
     * @return alias последнего join'a
     */
    private String parseJoins(String s) {
        String[] joinPath = s.split("\\.");     // отделяем join'ы от атрибутов
        String fieldName = joinPath[joinPath.length - 1];
        JoinObj pointer = rootJoin;
        for (int i = 0; i < (joinPath.length - 1); i++) {
            String currJoin = joinPath[i];                  // двигаемся по списку joinoв
            JoinObj newObj = pointer.getJoin(currJoin);     // параллельно двигая указатель в уже созданном дереве
            if (newObj == null) {                           // если не нашли, то создаем новый узел в дереве
                newObj = new JoinObj(currJoin, pointer);
                newObj.setAlias(currJoin + JoinObj.getInc());// не забываем добавить число к alias
                pointer.addJoin(newObj);                    // добавляем в Childs
            }
            pointer = newObj;                               // и сдвигаем указатель дальше по дереву
        }
        return pointer.getAlias();                          // вернем alias последнего join'a
    }


    /**
     * Возвращает корректную строку, которая подставляется в query на место where
     *
     * @return вышеописанная строка
     */
    public String getWhereClause() {
        StringBuilder result = new StringBuilder(" where 1 = 1 ");
        for (FilterParam filterParam : filterParams) {
            result.append(" and ");
            if (filterParam.getFilterType() == FilterType.LIKE) {
                result.append("lower(");
            }
            if (filterParam.getJoinName() == null) {
                result.append(rootJoin.getAlias());
            } else {
                result.append(filterParam.getJoinName());
            }
            result.append(".");
            result.append(filterParam.getFieldName());
            if (filterParam.getFilterType() == FilterType.LIKE) {
                result.append(")");
            }
            result.append(" ");
            result.append(filterParam.getFilterType().toString());
            result.append(" ");
            if (filterParam.getFilterType() != FilterType.NULLABLE) {
                result.append(" :");
                result.append(filterParam.getParamName());
            } else {
                result.append((Boolean) filterParam.getFieldValue() ? " null " : " not null ");
            }
            filterParam.getJoinName();
        }
        return result.toString();
    }

    /**
     * Возвращает корректную строку, которая подставляется в query на место joins (сразу после названия основной таблицы)
     *
     * @return вышеописанная строка
     */
    public String getJoinClause() {
        StringBuilder result = new StringBuilder(" ");
        rootJoin.traverse(result);
        return result.toString();
    }

    /**
     * Возвращает корректную строку, которая подставляется в query на место сортировки
     *
     * @return вышеописанная строка
     */
    public String getOrderByClause() {
        StringBuilder result = new StringBuilder();
        if (orderColumn != null && orderDir != null) {
            // TODO продумать сортировку по другим полям
            result.append(" ORDER BY entity.")
                    .append(orderColumn)
                    .append(" ")
                    .append(orderDir);
        }

        return result.toString();
    }

    /**
     * Вставляет необходимые параметры в созданную query
     *
     * @param query созданная query, которой необходимо выставить параметры
     * @return query с уже заполненными параметрами
     */
    public TypedQuery setParams(TypedQuery query) {
        for (FilterParam filterParam : filterParams) {
            if (filterParam.getFilterType() != FilterType.NULLABLE) {
                if (filterParam.getFilterType() == FilterType.LIKE) {
                    query.setParameter(filterParam.getParamName(), "%" + String.valueOf(filterParam.getFieldValue()).toLowerCase() + "%");
                } else {
                    query.setParameter(filterParam.getParamName(), filterParam.getFieldValue());
                }
            }
        }
        return query;
    }

    /**
     * Возвращает параметр
     *
     * @param paramName имя параметра
     * @return
     */
    public Object getParam(String paramName) {
        for (FilterParam filterParam : filterParams) {
            if (filterParam.getParamName().equals(paramName)) {
                return filterParam.getFieldValue();
            }
        }

        return null;
    }

    public Long getLimit() {
        return limit;
    }

    public Long getPage() {
        return page;
    }
}
