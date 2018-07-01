import React from "react";
import PropTypes from "prop-types";

class ProgressBar extends React.Component {

    constructor(props) {
        super(props);
    }


    render() {
        var pbContainerStyle = {width:this.props.width, height:this.props.height,position:'relative',borderStyle:'groove',borderColor:'lightgreen'};
        var pbValueStyle = {display:'inline-block',height:0.9*this.props.height,position:'absolute',textAlign:'center',left:this.props.width/2};
        var pbCompletedStyle = {width:(this.props.value/100)*this.props.width,height:this.props.height,backgroundColor:'lightgreen',display:'inline-block',
                                color:'black',fontWeight:'bolder',};
        var pbRemainingStyle = {width:((100-this.props.value)/100)*this.props.width,height:this.props.height,backgroundColor:'white',display:'inline-block'};

        return(
            <div id='pb-progress-bar' className='pb-progress-bar'>
                <div id='pb-container' className='pb-container' style={pbContainerStyle}>
                    <div className='pb-progress-value' style={pbValueStyle}>{this.props.value+'%'}</div>
                    <div className='pb-completed' style={pbCompletedStyle}></div>
                    <div className='pb-remaining' style={pbRemainingStyle}></div>
                </div>
            </div>
        );
    }
}

ProgressBar.defaultProps = {width:500,height:25,value:0};

ProgressBar.propTypes = {
    width : PropTypes.number.isRequired,
    height : PropTypes.number.isRequired,
    value : PropTypes.number.isRequired
};

export default ProgressBar;