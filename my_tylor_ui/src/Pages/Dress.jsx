import React, { useEffect, useState } from 'react'
import { Button, Form } from 'react-bootstrap';
import { useSearchParams } from 'react-router-dom';
import Navbar from '../Components/Navbar'
import { postApiService } from '../Services/apiService';

function Dress() {
  const [DressList, setDressList] = React.useState(null);
  const [searchParams] = useSearchParams();
  const [addDressBtnClicked, setAddCustomerBtnClicked] = React.useState(false);
  const [dressForm, setDressForm] = useState({});

  useEffect(() => {
    fetchDressList()
  }, [])

  async function fetchDressList() {
    let customerId = searchParams.get('customerId');
    let data = await postApiService("dressesForCustomer", { "customerId": customerId });
    setDressList(data['data']);
  }


  async function addDressFormChange(event) {
    let _tempDressForm = { [event.currentTarget.placeholder]: event.target.value }
    setDressForm({ ..._tempDressForm, ...dressForm })
  }

  async function addDressSubmit() {

    console.log(dressForm)
  }

  return (
    <>
      <Navbar />
      <div className='dress_component'>
        <Button 
          variant="outline-primary"
          className="dress_addDress_btn"
          onClick={() => { setAddCustomerBtnClicked(!addDressBtnClicked) }}>
          Add Customer
        </Button>
        {addDressBtnClicked &&
          <Form>
            {/* Name */}
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Name</Form.Label> 
              <Form.Control placeholder="name" onBlur={(event) => addDressFormChange(event)} />
            </Form.Group>

            {/* belly */}
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>belly</Form.Label>
              <Form.Control placeholder="belly" onBlur={(event) => addDressFormChange(event)} />
            </Form.Group>

            {/* waist */}
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>waist</Form.Label>
              <Form.Control placeholder="waist" onBlur={(event) => addDressFormChange(event)} />
            </Form.Group>

            {/* sholder */}
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>sholder</Form.Label>
              <Form.Control placeholder="sholder" onBlur={(event) => addDressFormChange(event)} />
            </Form.Group>

            {/* arm */}
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>arm</Form.Label>
              <Form.Control placeholder="arm" onBlur={(event) => addDressFormChange(event)} />
            </Form.Group>

            {/* leg */}
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>leg</Form.Label>
              <Form.Control placeholder="leg" onBlur={(event) => addDressFormChange(event)} />
            </Form.Group>

            <Button variant="primary" onClick={addDressSubmit}>
              Submit
            </Button>
          </Form>
        }
        {DressList && <table class="table table-bordered">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Name</th>
              <th scope="col">belly</th>
              <th scope="col">waist</th>
              <th scope="col">sholder</th>
              <th scope="col">arm</th>
              <th scope="col">leg</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {DressList !== null && DressList.map((dress) => (
              <tr>
                <td>{dress['id']}</td>
                <td>{dress['name']}</td>
                <td>{dress['measurements']['belly']}</td>
                <td>{dress['measurements']['waist']}</td>
                <td>{dress['measurements']['sholder']}</td>
                <td>{dress['measurements']['arm']}</td>
                <td>{dress['measurements']['leg']}</td>
                <td>
                  {/* <Button variant="primary" type="submit" onClick={() => { showDresses(cust['id']) }}>
                    Show Dresses
                  </Button> */}
                </td>
              </tr>
            ))}
          </tbody>
        </table>}
      </div>
      {/* <pre>{JSON.stringify(DressList, null, 4)}</pre> */}

    </>

  )
}

export default Dress