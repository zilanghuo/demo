package com.zilanghuo.java8.arithmetic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.Sets;

import java.util.*;

/**
 * Created by laiwufa on 2020-02-11
 */
public class TreeSeachDemo {


    public static List<Department> filterForMenus(List<Department> allPerms, List<Long> ownPermIds) {
        if (CollectionUtils.isEmpty(allPerms) || CollectionUtils.isEmpty(ownPermIds)) {
            return null;
        }
        Set<Department> perms = Sets.newHashSet();
        Map<Long, Department> permMap = new HashMap();
        allPerms.forEach(perm -> {permMap.put(perm.getId(), perm);});
        for (Long permId : ownPermIds) {
            List<Department> parentPerms = getParentPerms(permMap, permId);
            if (CollectionUtils.isNotEmpty(parentPerms)) {
                perms.addAll(parentPerms);
            }
        }
        List<Department> listDep =  Lists.newArrayList();
        Iterator<Department> iterator = perms.iterator();
        while (iterator.hasNext()){
            listDep.add(iterator.next());
        }
        return Lists.newArrayList();
    }

    private static List<Department> getParentPerms(Map<Long, Department> permMap, Long permId){
        if (permMap.get(permId) == null || permId == 0) {
            return null;
        }
        List<Department> allPerms = Lists.newArrayList();
        if (permMap.get(permId) != null) {
            allPerms.add(permMap.get(permId));
        }
        Long parentId = permMap.get(permId).getParentId();
        List<Department> parentPerms = getParentPerms(permMap, parentId);
        if (CollectionUtils.isNotEmpty(parentPerms)) {
            allPerms.addAll(parentPerms);
        }
        return allPerms;
    }

    /**
     * 递归获取权限
     * @param list
     * @return
     */
    public List<DepartTree> parseMenuTree(List<Department> list){

        List<DepartTree> result = new ArrayList();
        // 1、获取第一级节点
        for (Department menu : list) {
            if(0 == menu.getParentId()) {
                DepartTree resp = new DepartTree(menu);
                result.add(resp);
            }
        }
        // 2、递归获取子节点
        for (DepartTree parent : result) {
            parent = recursiveTree(parent, list);
        }
        return result;
    }

    public  DepartTree recursiveTree(DepartTree parent, List<Department> list) {
        for (Department menu : list) {
            if (parent.getId().equals(menu.getParentId())) {
                //按钮不显示在树形结构
                DepartTree resp = new DepartTree(menu);
                recursiveTree(resp, list);
                parent.getChilds().add(resp);
            }
        }
        return parent;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Department{
        private Long id;

        private Long name;

        private Long parentId;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class DepartTree{
        private Long id;

        private Long name;

        private Long parentId;

        private List<DepartTree> childs;

        public  DepartTree(Department menu) {
            // 构造数据
        }
    }
}

