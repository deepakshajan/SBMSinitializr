import React from 'react';
import PropTypes from 'prop-types';
import AtomicProcessPanel from "./AtomicProcessPanel.jsx";

class MainSection extends React.Component {

    constructor(props) {
        super(props);

    }

    render() {
        let innerContainerStyle = {width: '-webkit-fill-available'};
        let atomicProcessPanelStyle = {width:'99%',height:'inherit'};

        return (<div className='main-section-container' style={this.props.style}>
            <div className='main-section-inner-container' style={innerContainerStyle}>
                {this.props.loadContent && <AtomicProcessPanel title='Root' processDetails={this.props.processData} processList={this.props.processData.processTree.root.children} showChilds={true} isRoot={true} style={atomicProcessPanelStyle}/>}
            </div>
        </div>);
    }

}

MainSection.propTypes = {processData: PropTypes.object.isRequired, loadContent:PropTypes.bool.isRequired};

export default MainSection;