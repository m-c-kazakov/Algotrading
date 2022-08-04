import './App.css';
import React, {useState} from "react";
import CustomInput from "../CustomInput/CustomInput";
import CustomButton from "../CustomButton/CustomButton";
import axios from "axios";


const initialValues = {
    id: '',
    specificationOfStrategyId: '',
    score: '',
    valueOfAcceptableRisk: '',
    maximumPercentDrawdownFromStartScore: '',
    averagePercentDrawdownOfScore: '',
    maximumValueFromScore: '',
    numberOfWinningDeals: '',
    numberOfLosingDeals: ''
}


const initUserData = {
    userName: '',
    userPassword: '',
    submitType: ''
}

const baseUrl = 'http://localhost:8081'

const config = {
    headers: {
        'Content-Type': 'application/json'
    }
};

// axios.defaults.xsrfHeaderName = "X-CSRFToken"
axios.defaults.withCredentials = true



function App() {

    const [securityCookiesData, setSecurityCookiesData] = useState({
        authentication: false
    })

    const [statistics, setStatistics] = useState(findAllBook())


    const [userData, setUserData] = useState(initUserData)

    function findAllBook() {
        let statisticsData = []
        console.log(securityCookiesData.authentication)
        if (securityCookiesData.authentication) {
            axios.get(baseUrl + '/api/v1/statistics', config)
                .then(response => {
                    // console.log(response.data)
                    response.data
                        .map((book) => responseMapToBookData(book))
                        .forEach((book) => statisticsData.push(book))
                });
        }

        return statisticsData;
    }


    const isFilledUserDataFields = userData.userName && userData.userPassword


    const responseMapToBookData = (book) => {
        return {
            id: book.id,
            name: book.name,
            author: book.author,
            genre: book.genre
        }
    }


    const handleSubmitUser = (event) => {
        event.preventDefault();

        if (userData.submitType === 'Create') {
            console.log("isCreate")
            axios.post(baseUrl + '/api/v1/users', {
                username:  userData.userName,
                password: userData.userPassword
            }, config).then(response => {
                console.log("response: "+ response)
                setUserData(initUserData)
            })
        } else if (userData.submitType === 'Login') {
            console.log("isLogin");
            axios.post(baseUrl + '/login', {
                username:  userData.userName,
                password: userData.userPassword
            }, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(response => {
                if (response.status === 200) {
                    setSecurityCookiesData({
                        authentication: true
                    })
                }
            })
        } else {
            console.log("Не известный submitType: " + userData)
        }
    }

    const handleCleanClick = () => setUserData(initUserData);

    const handleInputUserChange = (event, fieldName) => {
        setUserData(prevState => ({
            ...prevState,
            [fieldName]: event.target.value
        }))
    }


    const handleClickCreateUser = (userData) => {
        setUserData(prevState => ({
            ...prevState,
            submitType: 'Create'
        }))
    }
    const handleClickLoginUser = (userData) => {
        setUserData(prevState => ({
            ...prevState,
            submitType: 'Login'
        }))
    }

    return (
        <div className={"wrapper"}>
            {/*TODO вернуть авторизацию*/}
            {securityCookiesData.authentication ?

                <div className={"wrapper-content"}>
                    <div>
                        <form onSubmit={handleSubmitUser} onReset={handleCleanClick}>
                            <CustomInput
                                placeholder={"Write User Name"}
                                handleChange={handleInputUserChange}
                                value={userData.userName}
                                fieldName={"userName"}
                            />
                            <CustomInput
                                placeholder={"Write User Password"}
                                handleChange={handleInputUserChange}
                                value={userData.userPassword}
                                fieldName={"userPassword"}
                            />
                            <div className={"buttons-wrapper"}>
                                <CustomButton
                                    label={"Create"}
                                    disabled={!isFilledUserDataFields}
                                    handleClick={handleClickCreateUser}
                                    data={userData}
                                    type={"submit"}
                                />
                                <CustomButton
                                    label={"Login"}
                                    disabled={!isFilledUserDataFields}
                                    handleClick={handleClickLoginUser}
                                    data={userData}
                                    type={"submit"}
                                />
                                <CustomButton
                                    label={"Clean"}
                                    type={"reset"}
                                />
                            </div>
                        </form>
                    </div>
                </div>

                :
                <div className={"wrapper-content"}>
                    <div className={"table-data"}>
                        <table>
                            <tbody>
                            <tr>
                                <th>Id</th>
                                <th>SpecificationOfStrategyId</th>
                                <th>Score</th>
                                <th>MaximumValueFromScore</th>
                                <th>NumberOfWinningDeals</th>
                                <th>NumberOfLosingDeals</th>
                            </tr>
                            {
                                statistics.map((statistic) => (
                                    <tr key={statistic.id}>
                                        <td>{statistic.id}</td>
                                        <td>{statistic.specificationOfStrategyId}</td>
                                        <td>{statistic.score}</td>
                                        <td>{statistic.maximumValueFromScore}</td>
                                        <td>{statistic.numberOfWinningDeals}</td>
                                        <td>{statistic.numberOfLosingDeals}</td>
                                    </tr>
                                ))
                            }

                            </tbody>
                        </table>
                        {/*<CustomTable*/}
                        {/*    books={statistics}*/}
                        {/*/>*/}
                    </div>
                </div>
            }
        </div>
    );
}

export default App;
