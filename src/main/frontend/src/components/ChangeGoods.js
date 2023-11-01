import { useEffect, useState } from "react";
import axios from "axios";
import "../css/registerGoods.css";
import { useNavigate, useParams } from "react-router-dom";

function RegisterGoods() {
  let { id } = useParams();
  let navigate = useNavigate();
  let [name, setName] = useState("");
  let [price, setPrice] = useState();
  let [information, setInformation] = useState("");
  let [mainFileImage, setMainFileImage] = useState("");
  let [addFileImage1, setAddFileImage1] = useState("");
  let [addFileImage2, setAddFileImage2] = useState("");
  let [category, setCategory] = useState("");
  let [thumb, setThumb] = useState([]);
  let [file, setFile] = useState([]);

  const [thumb1, setThumb1] = useState("");
  const [sub1, setSub1] = useState("");
  const [sub2, setSub2] = useState("");

  const [thumbImg, setThumbImg] = useState("");
  const [subImg1, setSubImg1] = useState("");
  const [subImg2, setSubImg2] = useState("");

  useEffect(() => {
    axios.get(`/shop/item/${id}`).then((res) => {
      console.log(res);
      setName(res.data.itemTitle);
      setPrice(res.data.price);
      setInformation(res.data.content);
    });
  }, []);

  useEffect(() => {
    axios
      .get(`/shop/item/${id}`)
      .then((res) => {
        setThumb1(res.data.thumb);
        setSub1(res.data.imageFiles[0]);
        setSub2(res.data.imageFiles[1]);
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(() => {
    Promise.all([
      axios.post(`/display/${thumb1?.storeFileName}`),
      axios.post(`/display/${sub1?.storeFileName}`),
      axios.post(`/display/${sub2?.storeFileName}`),
    ])
      .then((res) => {
        console.log(res);
        setThumbImg(res[0]?.data);
        setSubImg1(res[1]?.data);
        setSubImg2(res[2]?.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [sub2]);

  const formData = new FormData();
  formData.append("shopId", 1);
  formData.append("category", category);

  formData.append("itemTitle", name);

  formData.append("price", price);

  formData.append("content", information);

  formData.append("itemId", id);

  for (let i = 0; i < thumb.length; i++) {
    formData.append("thumb", file[i]);
  }

  for (let i = 0; i < file.length; i++) {
    formData.append("imageFiles", file[i]);
  }
  for (let key of formData.entries()) {
    console.log(key);
  }

  // for (let i = 0; i < imageFiles.length; i++) {
  //   formData.append("imageFiles", imageFiles[i]);
  // }

  // images.forEach((image) => formData.append("multipartFile", image));

  // const formData1 = new FormData();

  // for(let i = 0; i < formData1.length; i++) {
  //   formData1("imageFiles", formData1[i])
  // }

  function registerGoodsButton() {
    axios
      .post("/shop/saveItem", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then(() => {
        navigate("/shop");
      })
      .catch((err) => {
        console.log(err);
      });

    // axios
    //   .post(
    //     "/shop/saveItem",
    //     { shopId: 4, formData },
    //     {
    //       headers: {
    //         "Content-Type": "multipart/form-data",
    //       },
    //     }
    //   )
    //   .then((res) => {
    //     console.log(res);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
  }

  const saveFileMainImage = (e) => {
    setMainFileImage(URL.createObjectURL(e.target.files[0]));
    let copy = [...thumb];
    copy.push(e.target.files[0]);
    setThumb(copy);
    // console.log(e.target.files[0]);

    // formData.append("thumb", e.target.files[0]);
    for (let key of formData.entries()) {
      console.log(key);
    }
  };

  const saveFileAddImage1 = (e) => {
    setAddFileImage1(URL.createObjectURL(e.target.files[0]));
    let copy = [...file];
    copy.push(e.target.files[0]);
    setFile(copy);
    // console.log(e.target.files[0]);

    // formData.append("imageFiles", e.target.files[0]);
    for (let key of formData.entries()) {
      console.log(key);
    }
  };

  const saveFileAddImage2 = (e) => {
    setAddFileImage2(URL.createObjectURL(e.target.files[0]));
    let copy = [...file];
    copy.push(e.target.files[0]);
    setFile(copy);
    // console.log(e.target.files[0]);

    // formData.append("imageFiles", e.target.files[0]);
    for (let key of formData.entries()) {
      console.log(key);
    }
  };

  const categoryRadio = (e) => {
    let gender = e.target.value;
    gender === "male"
      ? setCategory(0)
      : gender === "female"
      ? setCategory(1)
      : setCategory(2);
  };

  return (
    <div className="registerMain">
      <table width="100%">
        <tr>
          <th className="title1">OffClothes</th>
        </tr>
        <tr>
          <th className="top">상품 수정</th>
        </tr>
        <tr>
          <td>
            <th className="registerList">상품 이름</th>
            <th>
              <input
                className="input"
                defaultValue={name}
                onChange={(e) => {
                  setName(e.target.value);
                  // formData.append(
                  //   "itemTitle",
                  //   new Blob([JSON.stringify(name)], {
                  //     type: "application/json",
                  //   })
                  // );
                  // formData1.append("itemTitle", name);
                  // for (let key of formData1.keys()) {
                  //   console.log(key);
                  // }
                  // for (let value of formData1.values()) {
                  //   console.log(value);
                  // }
                }}
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerList">메인 이미지 업로드</th>
            <th>
              {mainFileImage ? (
                <img className="imageUpload" alt="sample" src={mainFileImage} />
              ) : (
                <img className="imageUpload" alt="sample" src={thumbImg} />
              )}
              <div
                style={{
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <input
                  style={{ marginLeft: "1.5em" }}
                  name="imgUpload"
                  type="file"
                  accept="image/*"
                  onChange={saveFileMainImage}
                />
              </div>
            </th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerList">추가 이미지 업로드</th>
            <th>
              {addFileImage1 ? (
                <img className="imageUpload" alt="sample" src={addFileImage1} />
              ) : (
                <img className="imageUpload" alt="sample" src={subImg1} />
              )}
              <div
                style={{
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <input
                  style={{ marginLeft: "1.5em" }}
                  name="imgUpload1"
                  type="file"
                  accept="image/*"
                  onChange={saveFileAddImage1}
                />
              </div>
              {addFileImage2 ? (
                <img className="imageUpload" alt="sample" src={addFileImage2} />
              ) : (
                <img className="imageUpload" alt="sample" src={subImg2} />
              )}
              <div
                style={{
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <input
                  style={{ marginLeft: "1.5em" }}
                  name="imgUpload2"
                  type="file"
                  accept="image/*"
                  onChange={saveFileAddImage2}
                />
              </div>
            </th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerList">카테고리</th>
            <td className="radioButton">
              <input
                type="radio"
                id="female"
                name="gender"
                value="female"
                onChange={categoryRadio}
                // {}
              />
              <label for="">여성</label>
              <input
                type="radio"
                id="male"
                name="gender"
                value="male"
                onChange={categoryRadio}
              />
              <label for="">남성</label>
              <input
                type="radio"
                id="both"
                name="gender"
                value="both"
                onChange={categoryRadio}
              />
              <label for="">공용</label>
            </td>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerList">가격</th>
            <th>
              <input
                className="input"
                defaultValue={price}
                onChange={(e) => {
                  setPrice(e.target.value);
                }}
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td>
            <th className="registerList">상품 정보</th>
            <th>
              <input
                className="informationInput"
                defaultValue={information}
                onChange={(e) => {
                  setInformation(e.target.value);
                }}
              ></input>
            </th>
          </td>
        </tr>
        <tr>
          <td className="button">
            <button className="registerButton" onClick={registerGoodsButton}>
              수정
            </button>
            <button className="cancelButton">취소</button>
          </td>
        </tr>
      </table>
    </div>
  );
}

export default RegisterGoods;
