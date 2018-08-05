import React from "react";
import PropTypes from "prop-types";
import ReactDOM from "react-dom";

class FolderSelector extends React.Component {

    constructor(props) {
        super(props);

        this.validateAndSetState = this.validateAndSetState.bind(this);
    }

    render(){
        let fsStyleInner = {position:'relative',width:'100%'};
        let fsInputContainerStyle = {width:'97%',display:'inline-block'};
        let labelDivStyle = {width:'10%',display:'inline-block'};
        let labelStyle = {fontWeight:'bold',width:'100%'};
        let inputDivStyle = {width:'90%',display:'inline-block'};
        let inputStyle = {borderColor:'lightgreen',width: '100%'};
        let validationDivStyle = {width:'2%',display:'inline-block',float:'right'};
        let tickDefaultStyle = {fontWeight:'bolder',color:'green',margin:'unset',cursor:'default'};
        let crossDefaultStyle = {fontWeight:'bolder',color:'red',margin:'unset',cursor:'default'};

        return(<div className='folder-selector-container' style={this.props.style}>
            <div className='folder-selector-inner-container' style={fsStyleInner}>
                <div className='folder-selector-input-container' style={fsInputContainerStyle}>
                    <div style={labelDivStyle}>
                        <label htmlFor='folder-selector-input' style={labelStyle}>Directory : </label>
                    </div>
                    <div style={inputDivStyle}>
                        <input name='folder-selector-input' ref='folderSelectorInput' type='text' value={this.props.value} onChange={this.validateAndSetState} placeholder='Path to the micro service cluster(eg : D:\code\serviceCluster)' style={inputStyle}></input>
                    </div>
                </div>
                <div className='folder-selector-validation-status' style={validationDivStyle}>
                    {this.props.valid && <p id='folder-selector-tick' style={tickDefaultStyle} title='Valid Directory'>&#10004;</p>}
                    {!this.props.valid && <p id='folder-selector-cross' style={crossDefaultStyle} title='Invalid Directory'>&#10006;</p>}
                </div>
            </div>
        </div>);
    }

    validateAndSetState(event) {

        let valid = false;
        let value = ReactDOM.findDOMNode(this.refs.folderSelectorInput).value;
        if (this.checkForValidDirectory(value))
            valid = true;
        else
            valid = false;

        this.props.action(value, valid);
    }

    checkForValidDirectory(value) {
        let validDirectoryRegEx = new RegExp("^[A-Z]:(\\\\[a-z|A-Z|1-9]{1,99}){1,99}$");
        let valid = validDirectoryRegEx.test(value.trim());
        return valid;
    }

}

FolderSelector.propTypes = {
    value: PropTypes.string.isRequired,
    valid: PropTypes.bool.isRequired
};

export default FolderSelector;