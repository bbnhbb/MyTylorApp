import React, { useContext, useEffect, useRef, useState } from 'react'
import { Alert, Button, Form } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { Context } from '../Context/Store';
import { getApiService, postApiService } from '../Services/apiService'

function Customers() {
    let [customerList, setCustomerList] = useState(null);
    const [state, dispatch] = useContext(Context)
    const [addCustomerBtnClicked, setAddCustomerBtnClicked] = useState(false);
    const [custusername, SetCustusername] = useState(null);
    const [alertData, setAlertData] = useState(
        {
            show: false,
            variant: null,
            message: null
        }
    );
    let navigate = useNavigate();


    useEffect(() => {
        fetchCustomerList();
    }, [state])

    async function fetchCustomerList() {
        let data = await postApiService("customersForTylor", { "id": state['userData']['id'] });
        setCustomerList(data['data']);
    }

    const addCustomer = () => {
        setAddCustomerBtnClicked(true);
    }

    const addCustomerSubmit = async () => {
        let newUserToCreate = { "username": custusername, "tylor_id": customerList[0]['ownerId'] }
        let data = await postApiService("saveCusomers", newUserToCreate);
        if (data.status === 200) {
            setAlertData({
                show: true,
                variant: "success",
                message: "Customer saved successfully"
            })
            setAddCustomerBtnClicked(false);
            fetchCustomerList();
            setTimeout(() => {
                setAlertData({
                    show: false,
                    variant: null,
                    message: null
                })
            }, 5000);
        } else {
            setAlertData({
                show: true,
                variant: "danger",
                message: "Customer couldnt be saved successfully"
            })
        }
    }

    const showDresses = async (custId) => {
        navigate(`/dress?customerId=${custId}`);
    }


    return (
        <div className='customer_component'>
            {alertData.show && <Alert key={alertData.variant} variant={alertData.variant}>
                {alertData.message}
            </Alert>}
            <Button variant="outline-primary customer_addCust_btn" onClick={addCustomer}>Add Customer</Button>
            {addCustomerBtnClicked &&
                <Form>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>User Name</Form.Label>
                        <Form.Control type="email" placeholder="User Name" onChange={(event) => SetCustusername(event.target.value)} />
                    </Form.Group>

                    <Button variant="primary" type="submit" onClick={addCustomerSubmit}>
                        Submit
                    </Button>
                </Form>
            }
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">User Name</th>
                        <th scope="col">No of Dresses</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {customerList !== null && customerList.map((cust) => (
                        <tr>
                            <td>{cust['id']}</td>
                            <td>{cust['username']}</td>
                            <td>{cust['noOfDresses']}</td>
                            <td>
                                <Button variant="primary" type="submit" onClick={() => {showDresses(cust['id'])}}>
                                    Show Dresses
                                </Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {/* <pre>{JSON.stringify(customerList, null, 4)}</pre> */}
        </div>
    )
}

export default Customers