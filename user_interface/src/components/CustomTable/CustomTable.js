import React from "react";
import './CustomTable.css'


const CustomTable = (
    {
        statistics
    }
) => {

    return (
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
    );
}


export default CustomTable;