import './App.css';
import React, {useState} from "react";
import CustomInput from "../CustomInput/CustomInput";
import CustomButton from "../CustomButton/CustomButton";
import axios from "axios";


const initUserData = {
    userName: '',
    userPassword: '',
    submitType: ''
}

const baseUrl = 'http://localhost:8084'

const config = {
    headers: {
        'Content-Type': 'application/json'
    }
};

axios.defaults.withCredentials = true



function App() {

    const [securityCookiesData, setSecurityCookiesData] = useState({
        authentication: false
    })

    const [statisticsOfStrategy, setStatisticsOfStrategy] = useState([])



    const [userData, setUserData] = useState(initUserData)

    function getStatisticsOfStrategy() {
        console.log("getStatisticsOfStrategy")
        let statisticsOfStrategyData = []

        if (securityCookiesData.authentication) {
            axios.get(baseUrl + '/api/v1/statistics', config)
                .then(response => {
                    // console.log(response.data)
                    response.data
                        .map((statisticOfStrategyData) => responseMapToStatisticOfStrategyData(statisticOfStrategyData))
                        .forEach((statisticOfStrategyData) => statisticsOfStrategyData.push(statisticOfStrategyData))

                    // console.log(statisticsOfStrategyData.length)
                    if (statisticsOfStrategyData.length !== 0) {
                        console.log("statisticsOfStrategy")
                        // console.log(statisticsOfStrategy)
                        setStatisticsOfStrategy(statisticsOfStrategyData);
                    }
                });
        }



    }

    setInterval(getStatisticsOfStrategy, 5_000)
    // setImmediate


    const isFilledUserDataFields = userData.userName && userData.userPassword


    const responseMapToStatisticOfStrategyData = (statisticOfStrategyData) => {
        return {
            id: statisticOfStrategyData.id,
            specificationOfStrategyId: statisticOfStrategyData.specificationOfStrategyId,
            score: statisticOfStrategyData.score,
            maximumValueFromScore: statisticOfStrategyData.maximumValueFromScore,
            numberOfWinningDeals: statisticOfStrategyData.numberOfWinningDeals,
            numberOfLosingDeals: statisticOfStrategyData.numberOfLosingDeals
        }
    }





    const handleCleanUserDataClick = () => setUserData(initUserData);
    const handleSubmitUser = (event) => {
        event.preventDefault();

        if (userData.submitType === 'Create') {
            // console.log("isCreate")
            axios.post(baseUrl + '/api/v1/users', {
                username:  userData.userName,
                password: userData.userPassword
            }, config).then(response => {
                console.log("response: "+ response)
                setUserData(initUserData)
            })
        } else if (userData.submitType === 'Login') {
            // console.log("isLogin");
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
            {!securityCookiesData.authentication ?

                <div className={"wrapper-content"}>
                    <div>
                        <form onSubmit={handleSubmitUser} onReset={handleCleanUserDataClick}>
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
                    <table>
                        <tbody>
                        <tr>
                            <th>Id</th>
                            <th>specificationOfStrategyId</th>
                            <th>score</th>
                            <th>maximumValueFromScore</th>
                            <th>numberOfWinningDeals</th>
                            <th>numberOfLosingDeals</th>
                        </tr>
                        {
                            statisticsOfStrategy.map((statistic) => (
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
                </div>
            }
        </div>
    );
}

export default App;
