import React, { useContext, useState } from 'react';
import {
    MDBContainer,
    MDBTabs,
    MDBTabsItem,
    MDBTabsLink,
    MDBTabsContent,
    MDBTabsPane,
    MDBBtn,
    MDBIcon,
    MDBInput,
    MDBCheckbox
}
    from 'mdb-react-ui-kit';
import { postApiService } from '../Services/apiService';
import { useNavigate } from "react-router-dom";
import { Alert } from 'react-bootstrap';
import { Context } from '../Context/Store';

function LoginRegister() {

    const [justifyActive, setJustifyActive] = useState('tab1');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [state, dispatch] = useContext(Context);
    let navigate = useNavigate();
    const [loginStatus, setLoginStatus] = useState(null);
    const [registerStatus, setRegisterStatus] = useState(null);
    const [registerUserData, setRegisterUserData] = useState(null);
    

    const handleJustifyClick = (value) => {
        if (value === justifyActive) {
            return;
        }

        setJustifyActive(value);
    };

    const signIn = async () => {
        let response = await postApiService(
            'login',
            {
                "username": username,
                "password": password
            }
        );

        if (response.status === 200) {
            setLoginStatus("loggedin");
            localStorage.setItem("jwt", response.data.Authorization);
            dispatch({ type: 'ADD_USER', payload: response.data.user_data});
            dispatch({ type: 'ADD_JWT', payload: response.data.Authorization})
            navigate('/MyTylorApp/home');

        } else if (response.status === 401) {
            setLoginStatus("unauthorized")
        } else {
            setLoginStatus("loggin unsuccessfull");
        }

    }

    const handleRegisterInputChange = (e, inputLabel) => {
        if (!registerUserData) {
            setRegisterUserData({ [inputLabel]: e.target.value });
        } else {
            setRegisterUserData({ ...registerUserData, [inputLabel]: e.target.value });
        }
    }

    const register = async () => {

        let response = await postApiService(
            'register',
            registerUserData
        );

        if (response.status === 201) {
            setUsername(registerUserData['username']);
            setPassword(registerUserData['password']);
            setJustifyActive('tab1');
            setRegisterStatus("registered successfully");
        } else {
            setRegisterStatus("registration failure");
        }
        //  if (response.status === 400)
    }

    return (
        <>
            {loginStatus === "unauthorized" && <Alert key={"danger"} variant={"danger"}>
                Username or Password is incorrecct
            </Alert>}

            {loginStatus === "loggin unsuccessfull" && <Alert key={"danger"} variant={"danger"}>
                Loggin unsuccessfull try after some time
            </Alert>}

            {registerStatus === "registered successfully" && <Alert key={"success"} variant={"success"}>
                Registered Successfully
            </Alert>}

            {registerStatus === "registration failure" && <Alert key={"danger"} variant={"danger"}>
                Registeration failed
            </Alert>}


            <MDBContainer className="p-3 my-5 d-flex flex-column w-50">

                <MDBTabs pills justify className='mb-3 d-flex flex-row justify-content-between'>
                    <MDBTabsItem>
                        <MDBTabsLink onClick={() => handleJustifyClick('tab1')} active={justifyActive === 'tab1'}>
                            Login
                        </MDBTabsLink>
                    </MDBTabsItem>
                    <MDBTabsItem>
                        <MDBTabsLink onClick={() => handleJustifyClick('tab2')} active={justifyActive === 'tab2'}>
                            Register
                        </MDBTabsLink>
                    </MDBTabsItem>
                </MDBTabs>

                <MDBTabsContent>

                    <MDBTabsPane show={justifyActive === 'tab1'}>

                        <div className="text-center mb-3">
                            <p>Sign in with:</p>

                            <div className='d-flex justify-content-between mx-auto' style={{ width: '40%' }}>
                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <MDBIcon fab icon='facebook-f' size="sm" />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <MDBIcon fab icon='twitter' size="sm" />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <MDBIcon fab icon='google' size="sm" />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <MDBIcon fab icon='github' size="sm" />
                                </MDBBtn>
                            </div>

                            <p className="text-center mt-3">or:</p>
                        </div>

                        <MDBInput wrapperClass='mb-4' label='Username' id='form1' type='text' value={username}
                            onChange={(event) => { setUsername(event.target.value) }}
                        />
                        <MDBInput wrapperClass='mb-4' label='Password' id='form2' type='password' value={password}
                            onChange={(event) => { setPassword(event.target.value) }}
                        />

                        <div className="d-flex justify-content-between mx-4 mb-4">
                            <MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label='Remember me' />
                            <a href="!#">Forgot password?</a>
                        </div>

                        <MDBBtn className="mb-4 w-100" onClick={signIn}>Sign in</MDBBtn>
                        <p className="text-center">Not a member? <a href="#!">Register</a></p>

                    </MDBTabsPane>

                    <MDBTabsPane show={justifyActive === 'tab2'}>

                        <div className="text-center mb-3">
                            <p>Sign un with:</p>

                            <div className='d-flex justify-content-between mx-auto' style={{ width: '40%' }}>
                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <MDBIcon fab icon='facebook-f' size="sm" />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <MDBIcon fab icon='twitter' size="sm" />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <MDBIcon fab icon='google' size="sm" />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <MDBIcon fab icon='github' size="sm" />
                                </MDBBtn>
                            </div>

                            <p className="text-center mt-3">or:</p>
                        </div>

                        <MDBInput wrapperClass='mb-4' label='Username' id='form1' type='text' onBlur={(e) => handleRegisterInputChange(e, "username")} />
                        <MDBInput wrapperClass='mb-4' label='Password' id='form1' type='password' onBlur={(e) => handleRegisterInputChange(e, "password")} />

                        <div className='d-flex justify-content-center mb-4'>
                            <MDBCheckbox name='flexCheck' id='flexCheckDefault' label='I have read and agree to the terms' />
                        </div>

                        <MDBBtn className="mb-4 w-100" onClick={register}>Sign up</MDBBtn>

                    </MDBTabsPane>

                </MDBTabsContent>

            </MDBContainer>
        </>
    );
}

export default LoginRegister;