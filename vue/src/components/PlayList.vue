<template>
  <div class="formfield" id="userselect">
    <label for="categoryList">{{ labelText }}</label>
    <select id="categoryList" name="categoryList" class="formfield selectbox"
      @change="updateSelectionChoice" v-model="selectedId">
      <option value="">--- Please select a category ---</option>
      <!-- <option v-for="cat in categories" :key="cat.catId" :value="cat.apiCatId">
        {{ cat.name }}
      </option> -->
    </select>
  </div>
</template>