import { getElement, getStorageItem } from "./utils.js";

const title = getElement("#title");
const description = getElement("#description");
const category = getElement("#category");
const subCategory = getElement("#subCategory");
const submitBtn = getElement("#submit");
const result = getElement(".result");

window.addEventListener("DOMContentLoaded", async () => {
  try {
    let categories = await $.ajax({
      url: `/api/categories`,
      method: "get",
      dataType: "json",
    });

    category.innerHTML = categories
      .map((cat) => {
        return `<option value="${cat.id}">${cat.name}</option>`;
      })
      .join("");

    let subCategories = await $.ajax({
      url: `/api/categories/${category.value}/subcategories`,
      method: "get",
      dataType: "json",
    });

    subCategory.innerHTML = subCategories
      .map((subCat) => {
        return `<option value="${subCat.id}">${subCat.name}</option>`;
      })
      .join("");
  } catch (error) {}
});

category.addEventListener("change", async () => {
  try {
    let subCategories = await $.ajax({
      url: `/api/categories/${category.value}/subcategories`,
      method: "get",
      dataType: "json",
    });

    subCategory.innerHTML = subCategories
      .map((subCat) => {
        return `<option value="${subCat.id}">${subCat.name}</option>`;
      })
      .join("");
  } catch (error) {}
});

submitBtn.addEventListener("click", async (e) => {
  e.preventDefault();

  try {
    let newAd = {
      title: title.value,
      description: description.value,
      category: {
        id: subCategory.value,
      },
      publicationDate: new Date(),
      owner: {
        id: getStorageItem("userId"),
      },
    };

    console.log(newAd);

    if (
      title.value.trim().length !== 0 &&
      description.value.trim().length !== 0
    ) {
      let ad = await $.ajax({
        url: `/api/ads`,
        method: "post",
        dataType: "json",
        data: JSON.stringify(newAd),
        contentType: "application/json",
      });

      location.replace("./index.html");
    } else {
      result.innerHTML = `title and description must not be empty`;
    }
  } catch (error) {
    result.innerHTML = `Error creating new ad`;
  }
});
